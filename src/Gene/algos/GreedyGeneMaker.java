package Gene.algos;

import Gene.utils.Aligner;

/**
 * @Author: HUANG SHENYUAN
 * @Date: 4/22/2019 11:00 PM
 * @Algo: We select a fragment of the target gene from the head, the length is the
 * longest length of the gene fragment in the library, we align it with the sequence
 * in the gene pool, select the optimal fragment, and then continue, select the next
 * target gene fragment to match If the tail of the target gene fragment is redundant,
 * we move the selection starting point of the target gene to the beginning of redundancy.
 */
public class GreedyGeneMaker implements GeneMaker {
    private final String[] lib;
    private int maxLength;
    private int p_tail = 0;
    private int offset = 0;

    public GreedyGeneMaker(String[] lib) {
        this.lib = lib.clone();
        for (int i = 0; i < lib.length; i++)
            maxLength = lib[i].length() > maxLength ? lib[i].length() : maxLength;
    }

    public char[] produce(char[] target) {
        char[] product = new char[target.length + 2*maxLength];
        p_tail = 0;
        int t_tail = 0;
        while (p_tail < target.length) {
            t_tail -= offset;
            char[] targetFgt = getFragment(t_tail, t_tail + maxLength - 1, target);
            char[] bestFgt = bestFragment(targetFgt);
            t_tail += maxLength;
            addFgt(product, bestFgt);
        }
        return removeEmpty(product);
    }

    /**
     * @param fgt_target
     * @return
     * @version 01 simple match
     */
    private char[] bestFragment(char[] fgt_target) {
        int best = -999999999;
        char[] bestFgt = lib[0].toCharArray();
        for (int i = 0; i < lib.length; i++) {
            int s = new Aligner(fgt_target, lib[i].toCharArray()).getAlignScore();
            if (s > best) {
                best = s;
                bestFgt = lib[i].toCharArray();
            }
        }
        offset = new Aligner(fgt_target, bestFgt).getOffSetEnd();
//        if (offset > 0) System.out.println("ADJUST_BACK!");
        return bestFgt;
    }

    private char[] removeEmpty(char[] fgt) {
        int l = 0;
        for (int i = 0; i < fgt.length; i++)
            if (fgt[i] != 0) l++;
        char[] finalProduct = new char[l];
        for (int i = 0; i < l; i++)
            finalProduct[i] = fgt[i];
        return finalProduct;
    }

    private char[] getFragment(int start, int end, char[] target) {
        char[] fgt = new char[end - start +1];
        int index = 0;
        if (end > target.length) end = target.length - 1;
        for (int i = start; i <= end; i++){
            if(i>target.length - 1) break;
            fgt[index++] = target[i];
        }
        return fgt;
    }

    private void addFgt(char[] org, char[] fgt) {
        int length = fgt.length;
        int index = 0;
        while (length-- > 0)
            org[p_tail++] = fgt[index++];
    }
}
