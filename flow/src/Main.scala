/**
  * Created by viktor on 2017-03-27.
  */

import util.{BFS, Node}

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.{Source, StdIn}

object Main {
  def main(args: Array[String]): Unit = {}

  val railroad = Builder.railroad
  val root = railroad.find(_.name == "ORIGINS").get
  val goal = railroad.find(_.name == "48").get

  BFS.build(root, goal)
  println(BFS.getDistance(goal))
  println(BFS.getShortestPath(root, goal))

  }

