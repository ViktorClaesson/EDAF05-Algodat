package main

import java.io.File

import point.Point
import reader.FileReader

import scala.io.Source

/**
  * Created by Sebastian on 04/04/2017.
  */
object Tester {

  //Find all filenames
  val d = new File("data")
  val filenames = d.listFiles.map(f => f.toString).filterNot(name => name.contains("README") || name.contains("closest-pair-out")).toVector
  val outs: Map[String, String] = Source.fromFile("data/closest-pair-out.txt").getLines().map(l => {
    val li = l.split(" ");
    li(0).drop(8).dropRight(5) -> li(2)
  }).toMap

  def printAns(): Unit ={
    outs.foreach(println)
  }

  def printFilenames(): Unit ={
    filenames.foreach(println)
  }

  def testAll(): Unit ={
    for(name <- filenames){
      testOne(name)
    }
  }

  def testOne(name: String): Unit ={
    val ans = outs.get(name.drop(5).dropRight(8))
    if(name.contains("close") || name.contains("wc-instance")){
      val (p1, p2) = Algorithm.closest_pair(FileReader.read_close(name))
      print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}, Should be: N/A")
    }else{
      val (p1, p2) = Algorithm.closest_pair(FileReader.read_other(name))
      print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}, Should be: ${ans.get}")
    }
  }

}
