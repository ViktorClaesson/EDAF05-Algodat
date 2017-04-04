package main

import reader.ClosestPairs

/**
  * Created by Sebastian on 04/04/2017.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val vec1 = ClosestPairs.read()

    vec1.foreach(println)
  }
}
