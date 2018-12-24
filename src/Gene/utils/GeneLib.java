package Gene.utils;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: HUANG SHENYUAN
 * @Date: 4/14/2019 10:33 PM
 */
public class GeneLib {
    private int num;
    private int max;
    private static final char[] dic = {'A', 'B', 'C', 'D', 'E'};
    private String[] lib;

    public GeneLib(int num, int max) {
        this.num = num;
        this.max = max;
        lib = new String[num];
        this.generateLib();
    }

    public static char[] getRandomSeq(int m) {
        char[] seqR = new char[m];
        Random random = new Random();
        for (int i = 0; i < m; i++)
            seqR[i] = dic[random.nextInt(dic.length)];
        return seqR;
    }

    private void generateLib() {
        Set<String> seeds = new TreeSet<>();
        Random random = new Random();
        int length;
        while (seeds.size() < num) {
            length = random.nextInt(max - 1) + 2; // a seq is made up of at least two elements
            StringBuilder seq = new StringBuilder();
            while (length-- > 0)
                seq.append(dic[random.nextInt(dic.length)]);
            seeds.add(seq.toString());
        }
        int index = 0;
        for (String s : seeds) {
            lib[index++] = s;
        }
    }

    @Override
    public String toString() {
        StringBuilder libString = new StringBuilder();
        int sn = 1;
        for (String seq : lib)
            libString.append(sn++ + ":\t" + seq + "\n");
        return libString.toString();
    }

    public String[] getLib() {
        return lib.clone();
    }
}
