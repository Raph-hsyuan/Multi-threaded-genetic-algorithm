package Gene.algos;

import Gene.utils.Aligner;

import java.util.Random;

/**
 * @Author: HUANG SHENYUAN
 * @Date: 4/24/2019 4:12 PM
 * @Algo: We make the Genetic algorithm randomly and repeat 10000 times,
 * and chose the best solution of all.
 */
public class RandomGeneMaker implements GeneMaker {

    private String[] lib;

    public RandomGeneMaker(String[] lib) {
        this.lib = lib.clone();
    }

    @Override
    public char[] produce(char[] target) {
        int i = 0;
        int bestScore = -999999999;
        char[] bestProduct = null;
        Random random = new Random();
        while (i++ < 10000) {
            char[] p;
            StringBuilder builder = new StringBuilder();
            while (builder.length() < target.length)
                builder.append(lib[random.nextInt(lib.length)]);
            p = builder.toString().toCharArray();
            int score = new Aligner(target, p).getAlignScore();
            if (bestScore < score) {
                bestScore = score;
                bestProduct = p;
            }
        }
        return bestProduct;
    }
}
