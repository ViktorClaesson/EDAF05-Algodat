import util.{BFS, Node}

import scala.io.Source

/**
  * Created by Sebastian on 28/03/2017.
  */
class Tester() {

  private def run(title: String, nodes: Vector[Node], pairs: Vector[(String, String)], ans: Vector[Int]): Unit = {
    println("-"*10 + title + "-"*10)

    for (i <- 0 until pairs.length) {
      val root = nodes.find(n => n.name == pairs(i)._1).get
      val goal = nodes.find(n => n.name == pairs(i)._2).get
      val data = BFS.build(root, goal)
      val dist = BFS.getDistance(data)
      val shortestPath = BFS.getShortestPath(root, goal)
      val correct = ans(i) == dist

      println("Distance " + root + " -> " + goal + " = " + dist + " Correct: " + correct)
      println("Shortest path: " + shortestPath + "\n")
    }
  }

  def run10(nodes: Vector[Node]): Unit = {
    val pairs10: Vector[(String, String)] = Source.fromFile(Files.WORDS_10.dropRight(4) + "-in.txt").getLines().map(s => {
      val ss = s.split(" "); (ss(0), ss(1))
    }).toVector
    val ans10: Vector[Int] = Source.fromFile(Files.WORDS_10.dropRight(4) + "-out.txt").getLines().map(s => s.toInt).toVector

    run("10 words", nodes, pairs10, ans10)
  }

  def run50(nodes: Vector[Node]): Unit = {
    val pairs50: Vector[(String, String)] = Source.fromFile(Files.WORDS_50.dropRight(4) + "-in.txt").getLines().map(s => {
      val ss = s.split(" "); (ss(0), ss(1))
    }).toVector
    val ans50: Vector[Int] = Source.fromFile(Files.WORDS_50.dropRight(4) + "-out.txt").getLines().map(s => s.toInt).toVector

    run("50 words", nodes, pairs50, ans50)
  }

  def run250(nodes: Vector[Node]): Unit = {
    val pairs250: Vector[(String, String)] = Source.fromFile(Files.WORDS_250.dropRight(4) + "-in.txt").getLines().map(s => {
      val ss = s.split(" "); (ss(0), ss(1))
    }).toVector
    val ans250: Vector[Int] = Source.fromFile(Files.WORDS_250.dropRight(4) + "-out.txt").getLines().map(s => s.toInt).toVector

    run("250 words", nodes, pairs250, ans250)
  }

  def run500(nodes: Vector[Node]): Unit = {
    val pairs500: Vector[(String, String)] = Source.fromFile(Files.WORDS_500.dropRight(4) + "-in.txt").getLines().map(s => {
      val ss = s.split(" "); (ss(0), ss(1))
    }).toVector
    val ans500: Vector[Int] = Source.fromFile(Files.WORDS_500.dropRight(4) + "-out.txt").getLines().map(s => s.toInt).toVector

    run("500 words", nodes, pairs500, ans500)
  }

  def run5757(nodes: Vector[Node]): Unit = {
    val pairs5757: Vector[(String, String)] = Source.fromFile(Files.WORDS_5757.dropRight(4) + "-in.txt").getLines().map(s => {
      val ss = s.split(" "); (ss(0), ss(1))
    }).toVector
    val ans5757: Vector[Int] = Source.fromFile(Files.WORDS_5757.dropRight(4) + "-out.txt").getLines().map(s => s.toInt).toVector

    run("5757 words", nodes, pairs5757, ans5757)
  }

  def run104500(nodes: Vector[Node]): Unit = {
    val pairs104500: Vector[(String, String)] = Source.fromFile(Files.WORDS_104500.dropRight(4) + "-in.txt").getLines().map(s => {
      val ss = s.split(" "); (ss(0), ss(1))
    }).toVector
    val ans104500: Vector[Int] = Source.fromFile(Files.WORDS_104500.dropRight(4) + "-out.txt").getLines().map(s => s.toInt).toVector

    run("104500 words", nodes, pairs104500, ans104500)
  }
}
