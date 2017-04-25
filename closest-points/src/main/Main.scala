package main

import point.Point
import scala.io.Source
import java.io.File
/**
  * Created by Sebastian on 04/04/2017.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val time0 = System.currentTimeMillis()
    Tester.testAll()
    System.out.println(System.currentTimeMillis - time0)

    val time1 = System.currentTimeMillis()
    Tester.testAllSet()
    System.out.println(System.currentTimeMillis - time1)
  }
}
