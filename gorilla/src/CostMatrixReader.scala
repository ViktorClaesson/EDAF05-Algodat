/**
  * Created by Sebastian on 25/04/2017.
  */

import scala.io.Source

object CostMatrixReader {

  val costMatrix = "data/BLOSUM62.txt"

  def readMatrix(file: String): Vector[Vector[Int]] = {
    val header = Source.fromFile(file).getLines().drop(6).take(1).toString().split(" ")
    val matrix: Vector[Vector[Int]] = Source.fromFile(file).getLines().drop(7).map(l => l.split("\\s+").toVector.tail.map(_.trim.toInt)).toVector
    matrix
  }
}
