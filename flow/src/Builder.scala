import util.{Edge, Node}

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source

/**
  * Created by Sebastian on 29/03/2017.
  */
object Builder {

  lazy val railroad = buildNodesAndEdges(Files.RAIL)

  private def buildNodesAndEdges(path: String): (Vector[Node], Vector[Edge]) = {
    var index: Int = 0
    val lines = Source.fromFile(path).getLines().drop(1).take(55).zipWithIndex.toVector
    val nodes: Vector[Node] = for(n <- lines) yield new Node(n._1, n._2)

    val edges = Source.fromFile(path).getLines().drop(57).toVector
    edges.foreach(f = e => {
      val split = e.split("\\s")
      val orig: Node = nodes.find(n => n.index == split(0).toInt).get
      val dest: Node = nodes.find(n => n.index == split(1).toInt).get
      val capacity: Int = split(2).toInt

      val edgeToDest = new Edge(orig, dest, capacity)
      val edgeToOrigin = new Edge(dest, orig, capacity)
      edgeToDest.siblingEdge = edgeToOrigin
      edgeToOrigin.siblingEdge = edgeToDest

      orig.addEdge(edgeToDest)
      dest.addEdge(edgeToOrigin)
    })

    nodes.foreach(n => println(s"${n.adjacencyString}"))
    (nodes, edges)
  }
}

object Files{
  val RAIL = "data/rail.txt"
}


