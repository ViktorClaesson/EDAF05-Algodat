import scala.collection.mutable.ArrayBuffer

/**
  * Created by Sebastian on 25/04/2017.
  */
class Alignment(val str1: String, val str2: String) {

  val memoryMatrix = MemoryMatrix.createMatrix(str1, str2)
  var done: Boolean = false

  def alignment(costMatrix: Vector[Vector[Int]]): Int = {
    if (done)
      return memoryMatrix.last.last

    for (j <- 1 until memoryMatrix(0).length; i <- 1 until memoryMatrix.length) {
      memoryMatrix(i)(j) = opt(i, j, costMatrix)
    }
    memoryMatrix(memoryMatrix.length - 1)(memoryMatrix(0).length - 1) += getCost(str1.last, str2.last, costMatrix)
    done = true
    memoryMatrix.last.last
  }

  def buildWords(costMatrix: Vector[Vector[Int]]): (String, String) = {
    bind(buildWords(memoryMatrix.length - 1, memoryMatrix(0).length - 1, costMatrix), (str1.last.toString, str2.last.toString))
  }

  private def buildWords(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): (String, String) = {
    if(i == 0 && j == 0)
      return ("", "")

    val max = getAlternatives(i, j, costMatrix).zipWithIndex.max
    max._2 match {
      case 0 => return bind(buildWords(i - 1, j - 1, costMatrix), (str1(i - 1).toString, str2(j - 1).toString))
      case 1 => return bind(buildWords(i - 1, j, costMatrix), (str1(i - 1).toString, "_"))
      case default => return bind(buildWords(i, j - 1, costMatrix), ("_", str2(j - 1).toString))
    }
  }

  def bind(t0: (String, String), t1: (String, String)): (String, String) = {
    (t0._1 + t1._1, t0._2 + t1._2)
  }

  def matrixValue(i: Int, j: Int): Int = {
    if (i < 0 || j < 0)
      return Integer.MIN_VALUE
    else
      return memoryMatrix(i)(j)
  }

  def getAlternatives(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): Vector[Int] = {
    val cost = CostMatrixReader.emptyCost
    Vector(getCost(str1(i - 1), str2(j - 1), costMatrix) + matrixValue(i - 1, j - 1), cost + matrixValue(i - 1, j), cost + matrixValue(i, j - 1))
  }

  def opt(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): Int = {
    val max = getAlternatives(i, j, costMatrix).max
    max
  }

  def getCost(c1: Char, c2: Char, costMatrix: Vector[Vector[Int]]): Int = {
    costMatrix(CostMatrixReader.getIndex(c1))(CostMatrixReader.getIndex(c2))
  }

  def printMemoryMatrix(): Unit = {
    memoryMatrix.foreach(l => println(l.mkString(", ")))
  }
}
