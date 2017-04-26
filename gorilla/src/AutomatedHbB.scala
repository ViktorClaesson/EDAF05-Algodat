import scala.io.Source
import scala.collection.mutable.Map
/**
  * Created by Sebastian on 26/04/2017.
  */
object AutomatedHbB {

  val proteins: String = "data/HbB_FASTAs-in.txt"
  val solutions: String = "data/HbB_FASTAs-out.txt"
  val costMatrix: String = "data/BLOSUM62.txt"

  def readIn(file: String): Map[String, String] = {
    val proteins = Source.fromFile(file).getLines().toVector
    val map = Map.empty[String, String]

    var i = 0
    while (i < proteins.length) {
      if (proteins(i).startsWith(">")) {
        map.put(proteins(i).drop(1).takeWhile(c => c != ' '), proteins(i + 1) + proteins(i + 2) + proteins(i + 3))
      }
      i += 4
    }

    map
  }

  def test(): Unit ={
    val results = Source.fromFile(solutions).getLines().toVector
    val proteinMap = readIn(proteins)
    val cMatrix: Vector[Vector[Int]] = CostMatrixReader.readMatrix(costMatrix)

    var i = 0
    while(i < results.length){
      val names = results(i).takeWhile(c => c != ':').split("--").
      val name1 = names(0)
      val name2 = names(1)
      val trueRes = results(i).dropWhile(c => c != ' ').trim.toInt

      val prot1 = proteinMap(name1)
      val prot2 = proteinMap(name2)

      val alignment = new Alignment(prot1, prot2)
      val result = alignment.alignment(cMatrix)
      val words = alignment.buildWords(cMatrix)

      println(name1 + "--" + name2)
      if (result != trueRes) println(s"FAILED! Found: $result, expected $trueRes") else println(s"SUCCESS! $result = $trueRes")

      println(words._1 + "\n" + words._2 + "\n")

      i+= 3
    }
  }

  def main(args: Array[String]): Unit = {
    test()
  }
}
