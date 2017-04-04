package reader

import point.Point

import scala.io.Source

import scala.collection.mutable.Map
/**
  * Created by Sebastian on 04/04/2017.
  */
object FileReader {

  def read(name: String, dropEnd: Int): Vector[Point] = {
    val vec = Source.fromFile(name).getLines().toVector.dropWhile(l => l != "NODE_COORD_SECTION").drop(1).filter(l => l != "" && l != " ").dropRight(dropEnd).map(l => toPoint(l))
    vec
  }

  def toPoint(line: String): Point = {
    val split = line.split(" ").filter(_.length != 0)
    val p = new Point(split(0), split(1), split(2))
    p
  }
}

