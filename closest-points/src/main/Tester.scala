package main

import java.io.File

import point.Point
import reader.FileReader

/**
  * Created by Sebastian on 04/04/2017.
  */
object Tester {

  //Find all filenames
  val d = new File("data")
  val filenames = d.listFiles.map(f => f.toString).filterNot(name => name.contains("README") || name.contains("closest-pair-out")).toVector.sorted
  //val outs =

  def printFilenames(): Unit ={
    filenames.foreach(println)
  }

  def testAll(): Unit ={
    for(name <- filenames){
      if(name.contains("close") || name.contains("wc-instance")){
        val (p1, p2) = Algorithm.closest_pair(FileReader.read_close(name))
        print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}")
      }else{
        val (p1, p2) = Algorithm.closest_pair(FileReader.read_other(name))
        print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}")
      }
    }
  }

  def testOne(name: String): Unit ={
    if(name.contains("close") || name.contains("wc-instance")){
      val (p1, p2) = Algorithm.closest_pair(FileReader.read_close(name))
      print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}")
    }else{
      val (p1, p2) = Algorithm.closest_pair(FileReader.read_other(name))
      print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}")
    }
  }

}
