package Gene.tests;

import Gene.algos.GeneMaker;
import Gene.algos.GeneticGeneMaker;
import Gene.algos.GreedyGeneMaker;
import Gene.algos.RandomGeneMaker;
import Gene.utils.Aligner;
import Gene.utils.GeneLib;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int a = 5;
        char[] tar = GeneLib.getRandomSeq(50 * a);
        String[] lib = new GeneLib(10 * a, 5 * a).getLib();
        long starTime = System.currentTimeMillis();
        /*-------------------------------------
         *              test1 start
         * -------------------------------------*/
        //Greedy method
        System.out.println("=======================\nGreedy method ----/////////");
        GeneMaker maker = new GreedyGeneMaker(lib);
        Aligner aligner = new Aligner(tar, maker.produce(tar));
        aligner.printResult();
        /*-------------------------------------
         *                test1 end
         * -------------------------------------*/
        long endTime = System.currentTimeMillis();
        long Time = endTime - starTime;
        System.out.println("\nrunning time Greedy : " + Time + " ms");



        /*-------------------------------------
         *              test2 start
         * -------------------------------------*/
        //Random method
        System.out.println("=======================\nRandom search ----/////////");
        GeneMaker maker1 = new RandomGeneMaker(lib);
        Aligner aligner1 = new Aligner(tar, maker1.produce(tar));
        aligner1.printResult();
        /*-------------------------------------
         *                test2 end
         * -------------------------------------*/
        long endTime2 = System.currentTimeMillis();
        long Time2 = endTime2 - endTime;
        System.out.println("\nrunning time Random : " + Time2 + " ms");


        /*-------------------------------------
         *              test3 start
         * -------------------------------------*/
        //Random method
        System.out.println("=======================\nHeuristic Genetic ----/////////");
        GeneMaker maker2 = new GeneticGeneMaker(lib);
        Aligner aligner2 = new Aligner(tar, maker2.produce(tar));
        aligner2.printResult();
        /*-------------------------------------
         *                test3 end
         * -------------------------------------*/
        long endTime3 = System.currentTimeMillis();
        long Time3 = endTime3 - endTime2;
        System.out.println("\nrunning time Heuristic : " + Time3 + " ms");













        // compare with randomly generated seq
        Random ran = new Random();
        System.out.println("=======================\nOne Random score : ");
        StringBuilder randBuilder = new StringBuilder();
        while (randBuilder.length() < tar.length)
            randBuilder.append(tar[ran.nextInt(tar.length - 1)]);
        new Aligner(tar, randBuilder.toString().toCharArray()).printResult();
    }
}
