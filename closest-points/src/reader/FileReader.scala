package reader

import point.Point

import scala.io.Source

import scala.collection.mutable.Map
/**
  * Created by Sebastian on 04/04/2017.
  */
object FileReader {


}

object ClosestPairs{

  def read(): Vector[Point] ={
    val vec = Source.fromFile("data/close-pairs-1-in.txt").getLines().map(l => toPoint(l)).toVector
    vec
  }

  def toPoint(line: String): Point = {
    val split = line.split(" ")
    val p = new Point(split(0), split(1), split(2))
    p
  }
}

