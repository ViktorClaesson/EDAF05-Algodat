/**
  * <<<<<<< HEAD
  * Created by viktor on 2017-04-25.
  */
object Main {

  def main(args: Array[String]): Unit = {

    val costMatrix = CostMatrixReader.readMatrix("data/BLOSUM62.txt")
    //costMatrix.foreach(l => println(l.mkString(", ")))

    val al = new Alignment("KQRK", "KAK")
    val cost = al.alignment(CostMatrixReader.readMatrix("data/BLOSUM62.txt"))
    al.printMemoryMatrix()

    println(cost)

    println(al.aligned1 + "\n" + al.aligned2)
  }
}
