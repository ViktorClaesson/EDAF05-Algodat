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
}
