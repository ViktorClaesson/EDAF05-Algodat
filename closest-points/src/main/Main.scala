package main

import point.Point
import reader.ClosestPairs

/**
  * Created by Sebastian on 04/04/2017.
  */
object Main {

  def main(args: Array[String]): Unit = {
    for(i <- 1 to 6) {
      println(i)
      val vec1 = ClosestPairs.read(i)
      println(brute_force(vec1))
    }
  }

  def closest_pair(points: Vector[Point]): (Point, Point) = {
    (null, null)
  }

  def brute_force(points: Vector[Point]): (Point, Point) = {
    var pair: (Point, Point) = (null, null)
    var lowestDist = Double.MaxValue
    points.foreach(p1 => points.foreach(p2 => {
      if(p1 != p2) {
        val dist = p1.dist(p2)
        if (dist < lowestDist) {
          pair = (p1, p2)
          lowestDist = dist
        }
      }
    }))
    pair
  }
}
