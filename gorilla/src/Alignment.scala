import scala.collection.mutable.ArrayBuffer

/**
  * Created by Sebastian on 25/04/2017.
  */
class Alignment (val str1: String, val str2: String){

  val memoryMatrix = MemoryMatrix.createMatrix(str1, str2)
  var aligned1: String = ""
  var aligned2: String = ""

  def alignment(costMatrix: Vector[Vector[Int]]): Int ={
    for(j <- 1 until memoryMatrix(0).length; i <- 1 until memoryMatrix.length){
      memoryMatrix(i)(j) = opt(i, j, costMatrix)
    }

    memoryMatrix.last.last + getCost(str1.last, str2.last, costMatrix)
  }

  def opt(i: Int, j: Int, costMatrix: Vector[Vector[Int]]): Int ={
    val cost = CostMatrixReader.emptyCost
    val max = Vector(getCost(str1(i-1), str2(j-1), costMatrix) + memoryMatrix(i-1)(j-1), cost + memoryMatrix(i-1)(j), cost + memoryMatrix(i)(j-1)).zipWithIndex.max

    max match {
      case (v, 0) => aligned1 += str1(i-1); aligned2 += str2(j-1); return v
      case (v, 1) => aligned2 += '_'; return v
      case default => aligned1 += '_'; return max._1
    }
  }

  def getCost(c1: Char, c2: Char, costMatrix: Vector[Vector[Int]]): Int ={
    costMatrix(CostMatrixReader.getIndex(c1))(CostMatrixReader.getIndex(c2))
  }

  def printMemoryMatrix(): Unit ={
    memoryMatrix.foreach(l => println(l.mkString(", ")))
  }
}
