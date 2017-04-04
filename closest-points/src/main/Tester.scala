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
  val filenames = d.listFiles.map(f => f.toString).filterNot(name => name.contains("README")).toVector

  def test(): Unit ={
    for(name <- filenames){
      if(name.contains("close") || name.contains("wc-instance")){
        val (p1, p2) = Algorithm.closest_pair(FileReader.read(name, 0))
        print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}")
      }else{
        val (p1, p2) = Algorithm.closest_pair(FileReader.read(name, 1))
        print(name + ": "); println(s"${p1.name} -> ${p2.name}: ${p1.dist(p2)}")
      }
    }
  }

}
