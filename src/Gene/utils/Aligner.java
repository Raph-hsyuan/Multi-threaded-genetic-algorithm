package Gene.utils;

import java.util.Stack;

/**
 * @Author: HUANG SHENYUAN
 * @Date: 4/15/2019 10:37 PM
 * @Version using for loop
 */
public class Aligner {

    public static final int INDEL = -1;
    public static final int MISMATCH = -1;
    public static final int MATCH = 1;
    char[] target; // one seq genetic
    char[] product; // another seq genetic
    int[][] transfer;
    int[][] alignTable;
    int backOffSet;
    Stack<Character> targetNew;
    Stack<Character> productNew;
    int score;

    public Aligner(char[] target, char[] product) {
        score = 0;
        this.target = target;
        this.product = product;
        alignTable = new int[target.length + 1][product.length + 1];
        transfer = new int[target.length + 1][product.length + 1];
        targetNew = new Stack<>();
        productNew = new Stack<>();
        buildTable();
        //print(alignTable);
        //print(transfer);
        analysePath();
        analyseOffSet();
    }

    /**
     * transfer(x,y) = 0 if its predecessor is (x-1,y)
     * transfer(x,y) = 1 if its predecessor is (x-1,y-1)
     * transfer(x,y) = 2 if its predecessor is (x,y-1)
     */
    private void buildTable() {
        initial();
        for (int i = 1; i < target.length + 1; i++)
            for (int j = 1; j < product.length + 1; j++) {
                int a = alignTable[i - 1][j - 1] + align(target[i - 1], product[j - 1]);
                int b = alignTable[i][j - 1] + align(target[i - 1], '-');
                int c = alignTable[i - 1][j] + align('-', product[j - 1]);
                int result = a > b ? (a > c ? a : c) : (b > c ? b : c);
                alignTable[i][j] = result;
                transfer[i][j] = result == a ? 1 : (result == b ? 2 : 0);
            }

    }

    /**
     * @param a one char in dict
     * @param b another char in dict
     * @return we can set different mismatch Value(x,y) which x,y from dict
     */
    private int align(char a, char b) {
        if (a == b) return MATCH;
        if (a == '-' || b == '-') return INDEL;
        else return MISMATCH;
    }

    private void initial() {
        for (int i = 0; i < target.length; i++) {
            alignTable[i + 1][0] = alignTable[i][0] + align(target[i], '-');
            transfer[i + 1][0] = 0;
        }
        for (int j = 0; j < product.length; j++) {
            alignTable[0][j + 1] = alignTable[0][j] + align('-', product[j]);
            transfer[0][j + 1] = 2;
        }
    }

    public int getAlignScore() {
        return score;
    }

    private void analysePath() {
        int deep = 0;
        int i = target.length;
        int j = product.length;
        score = alignTable[i][j];
        while (deep-- < target.length + product.length) {
            if (i + j == 0) break;
            switch (transfer[i][j]) {
                case 0:
                    targetNew.push(target[--i]);
                    productNew.push('-');
                    break;
                case 1:
                    targetNew.push(target[--i]);
                    productNew.push(product[--j]);
                    break;
                case 2:
                    productNew.push(product[--j]);
                    targetNew.push('-');
                    break;
                default:
                    throw new RuntimeException("Unexpected table");
            }
        }
    }

    public void printResult() {
        System.out.print("\nAlign Result:\n\tTarget seq\t: ");
        while (!targetNew.isEmpty())
            System.out.print(targetNew.pop());
        System.out.print("\n\tProduct seq\t: ");
        while (!productNew.isEmpty())
            System.out.print(productNew.pop());
        System.out.println("\nBest score : " + score);
    }

//    public int getOffSetFront() {
//        return frontOffSet;
//    }

    public int getOffSetEnd() {
        return backOffSet;
    }

    private void print(int[][] table) {
        System.out.print("\t");
        for (int j = 0; j < product.length; j++) System.out.print("\t" + product[j]);
        for (int i = 0; i < target.length + 1; i++) {
            System.out.print("\n");
            if (i > 0) System.out.print(target[i - 1] + "\t");
            else System.out.print("\t");
            for (int j = 0; j < product.length + 1; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void analyseOffSet() {
        Stack<Character> stack = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        stack.addAll(productNew);
        int counter = 0;
        while (!stack.isEmpty()) stack2.push(stack.pop());
        while (stack2.pop() == '-') counter++;
        backOffSet = counter;
    }
}
