import scala.collection.mutable.ArrayBuffer

/**
  * Created by Sebastian on 25/04/2017.
  */
class Alignment(val str1: String, val str2: String) {

  val memoryMatrix = MemoryMatrix.createMatrix(str1, str2)

  // --------------------------------- FILLING MEMORYMATRIX ---------------------------------

  def alignment(costMatrix: Vector[Vector[Int]]): Int = {
    for (j <- 1 until memoryMatrix(0).length; i <- 1 until memoryMatrix.length) {
      memoryMatrix(i)(j) = opt(i, j, costMatrix)
    }

    memoryMatrix.last.last
  }

  def opt(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): Int = {
    val max = getAlternatives(i, j, costMatrix).max
    max
  }

  // --------------------------------- BUILDING WORDS ---------------------------------

  def buildWords(costMatrix: Vector[Vector[Int]]): (String, String) = {
    buildWords(memoryMatrix.length - 1, memoryMatrix(0).length - 1, costMatrix)
  }

  private def buildWords(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): (String, String) = {
    if(i == 0 && j == 0)
      return ("", "")

    val max = getAlternatives(i, j, costMatrix).zipWithIndex.max
    max._2 match {
      case 0 => return bind(buildWords(i - 1, j - 1, costMatrix), (str1(i - 1).toString, str2(j - 1).toString))
      case 1 => return bind(buildWords(i - 1, j, costMatrix), (str1(i - 1).toString, "-"))
      case default => return bind(buildWords(i, j - 1, costMatrix), ("-", str2(j - 1).toString))
    }
  }

  // --------------------------------- HELP METHODS ---------------------------------

  def matrixValue(i: Int, j: Int): Int = {
    if (i < 0 || j < 0)
      return -1e9.toInt
    else
      return memoryMatrix(i)(j)
  }

  def getAlternatives(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): Vector[Int] = {
    val cost = CostMatrixReader.emptyCost
    Vector(getCost(i - 1, j - 1, costMatrix) + matrixValue(i - 1, j - 1), cost + matrixValue(i - 1, j), cost + matrixValue(i, j - 1))
  }

  def bind(t0: (String, String), t1: (String, String)): (String, String) = {
    (t0._1 + t1._1, t0._2 + t1._2)
  }

  def getCost(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): Int = {
    if(i < 0 || j < 0)
      return -1e9.toInt;

    costMatrix(CostMatrixReader.getIndex(str1(i)))(CostMatrixReader.getIndex(str2(j)))
  }

  def printMemoryMatrix(): Unit = {
    memoryMatrix.foreach(l => println(l.mkString(", ")))
  }
}
