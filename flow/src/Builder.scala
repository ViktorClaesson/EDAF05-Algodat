import util.{Edge, Node}

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source

/**
  * Created by Sebastian on 29/03/2017.
  */
object Builder {

  lazy val railroad = buildNodes(Files.RAIL)

  private def buildNodes(path: String): Vector[Node] = {
    var index: Int = 0
    val lines = Source.fromFile(path).getLines().drop(1).take(55).zipWithIndex.toVector
    val nodes: Vector[Node] = for(n <- lines) yield new Node(n._1, n._2)

    val edges = Source.fromFile(path).getLines().drop(57)
    edges.foreach(f = e => {
      val split = e.split("\\s")
      val orig: Node = nodes.find(n => n.index == split(0).toInt).get
      val dest: Node = nodes.find(n => n.index == split(1).toInt).get
      val capacity: Int = split(2).toInt
      orig.addEdge(new Edge(dest, capacity))
      dest.addEdge(new Edge(orig, capacity))
    })

    nodes.foreach(n => println(s"${n.adjacencyString}"))
    nodes
  }
}

object Files{
  val RAIL = "data/rail.txt"
}


