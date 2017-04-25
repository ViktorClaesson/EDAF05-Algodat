import scala.io.Source

/**
  * Created by Sebastian on 25/04/2017.
  */

import scala.io.Source

object CostMatrixReader {

  val order = List('A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V', 'B', 'Z', 'X', '*');

  def getIndex(c: Char) = (order.indexOf(c) + order.length) % order.length

  def readMatrix(file: String): Array[Array[Int]] = {
    Source.fromFile(file).getLines().drop(7).map(arr => arr.drop(1).trim.split("\\s+")).map(_.map(_.toInt)).toArray;
  }
  
}
