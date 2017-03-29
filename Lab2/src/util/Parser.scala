package util

import scala.io.StdIn

/**
  * Created by Sebastian on 29/03/2017.
  */
object Parser {

  def run(nodes: Vector[Node]): Unit = {

    while(true){
      readInput(nodes)
    }
  }

  def readInput(nodes: Vector[Node]): Unit = {
    val rootStr = StdIn.readLine("Input root node: ")
    val goalStr = StdIn.readLine("Input goal node: ")

    val root = nodes.find(n => n.name == rootStr)
    val goal = nodes.find(n => n.name == goalStr)
    var bfsNode: Node = null

    if (root.isEmpty || goal.isEmpty) {
      println("Goal or Root does not exist")
      return
    }
    else {
      bfsNode = BFS.build(root.get, goal.get)
    }

    println("-" * 30)
    println("Distance " + root.get + " -> " + goal.get + " = " + BFS.getDistance(bfsNode))
    println("Shortest path: " + BFS.getShortestPath(root.get, bfsNode))
  }
}
