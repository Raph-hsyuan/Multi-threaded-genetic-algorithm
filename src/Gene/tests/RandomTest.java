package Gene.tests;

import Gene.algos.GeneMaker;
import Gene.algos.RandomGeneMaker;
import Gene.utils.Aligner;
import Gene.utils.GeneLib;


/**
 * @Author: HUANG SHENYUAN
 * @Date: 4/29/2019 3:03 AM
 */
public class RandomTest {
    public static final int TIMES = 100;

    public static void main(String[] args) {
        for (int a = 1; a < 20; a++) {
            int scoreTotal = 0;
            int timeTotal = 0;
            for (int i = 0; i < TIMES; i++) {
                char[] tar = GeneLib.getRandomSeq(50 * a);
                String[] lib = new GeneLib(10 * a, 5 * a).getLib();
                long starTime = System.currentTimeMillis();
                /*-------------------------------------
                 *              test1 start
                 * -------------------------------------*/
                GeneMaker maker = new RandomGeneMaker(lib);
                Aligner aligner = new Aligner(tar, maker.produce(tar));
                /*-------------------------------------
                 *                test1 end
                 * -------------------------------------*/
                long endTime = System.currentTimeMillis();
                long Time = endTime - starTime;
                timeTotal += Time;
                scoreTotal += aligner.getAlignScore();
            }
            System.out.println("a = " + a + ": time :" + timeTotal * 1.0 / TIMES + "Score :" + scoreTotal * 1.0 / TIMES);
        }
    }
}
