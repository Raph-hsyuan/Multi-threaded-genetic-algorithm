
# A simple application of a genetic algorithm


> 基于遗传物质基因连接的算法问题，如果X和Y分别是由固定符号集{a,b,c,d,e}所生成的序列，XY是由连接这些符号元素所生成的序列，顺序为先X后Y。专家们已经确定了一个遗传物质的靶序列C，它由m个符号元素组成，且他们想要尽可能的制造出一个与C类似的序列。为此他们建立了一个L库，它由k个相对更短的序列组成，每个序列的的长度最长为n。他们可以通过连接拷贝序列库L中所含序列以最小的代价生产出序列（允许重复），所以我们说一个基于L库的连接串是一个形式为B1B2...Bl的任意序列，所有Bi都来自L。问题是要找到一个基于{Bi}的连接串，它的对齐成本要尽可能的小，为了计算对齐序列串的代价，你可以假设你有一个插入/缺失标记InDel (insertion-deletion)的代价 和一个对于任意对p,q属于{a,b,c,d,e}的不匹配代价。 

| Fonction  | Implement | Test |
| ------ | ------ | ------ |
| Generate Lib | [GeneLib](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/utils/GeneLib.java)| [test](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/tests/Main.java) |
| Alignement | [AlignCost](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/utils/Aligner.java)| [test](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/tests/Main.java)|
| Algo01 | [Greedy](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/algos/GreedyGeneMaker.java)| [test](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/tests/GreedyTest.java)|
| Algo02 | [Random](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/algos/RandomGeneMaker.java)| [test](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/tests/RandomTest.java)|
| Algo03 | [Genetic_Multi-threads](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/algos/GeneticGeneMaker.java)| [test](https://github.com/huangshenyuan-unice/algo_avenc-/blob/master/src/Gene/tests/HeuristicTest.java)|


Inspired by:[https://www.canal-u.tv/producteurs/inria/cours_en_ligne/bioinformatique_algorithmes_et_genomes](https://www.canal-u.tv/producteurs/inria/cours_en_ligne/bioinformatique_algorithmes_et_genomes)
