/**
  * Created by viktor on 2017-04-25.
  */
object Main {

  def main(args: Array[String]): Unit = {

    val costMatrix = CostMatrixReader.readMatrix("data/BLOSUM62.txt")
    //costMatrix.foreach(l => println(l.mkString(", ")))

    val al = new Alignment("KQRIKAAKABK", "KAK")
    val cost = al.alignment(CostMatrixReader.readMatrix("data/BLOSUM62.txt"))
    val aligned = al.buildWords(costMatrix)
    println(cost)
    println(aligned._1)
    println(aligned._2)
  }
}
