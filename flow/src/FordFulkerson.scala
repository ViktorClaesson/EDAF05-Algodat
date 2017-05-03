import util.{BFS, Node, Path}

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Queue

/**
  * Created by Sebastian on 02/05/2017.
  */
object FordFulkerson {

  def run(s: Node, t: Node): Unit ={
    while(BFS.build(s, t)) {
      val path = new Path(s, t)
      path.updateResidualCapacity()
    }

    val minCut = minimalCut(s)
    val edgeEdges = minCut.map(n => n.adjacencyList).flatten.distinct
      .filter(e => e.residualCapacity == 0)
      .filterNot(e => minCut.contains(e.terminalNode))
    edgeEdges.sortBy(_.startingNode.index).foreach(println)
    printf("Sum: %d\n", edgeEdges.map(e => e.capacity).sum)
  }

  // Returns a Vector of all the nodes that are in the minimal cut
  def minimalCut(root: Node): Vector[Node] = {
    val visitedNodes = ArrayBuffer(root)
    val queue = Queue(root)

    do {
      val current = queue.dequeue()
      val edges = current.adjacencyList
        .filterNot(e => e.residualCapacity == 0)
        .filterNot(e => visitedNodes.contains(e.terminalNode))
      visitedNodes ++= edges.map(e => e.terminalNode)
      edges.foreach(e => queue.enqueue(e.terminalNode))
    } while (!queue.isEmpty)

    visitedNodes.toVector
  }
}