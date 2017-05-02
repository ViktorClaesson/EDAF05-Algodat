package util

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Sebastian on 02/05/2017.
  */
class Path (root: Node, goal: Node){

  val edges = ArrayBuffer.empty[Edge]

  def updateResidualCapacity() = {
    val min = getMinResidualCapacity(root)
    edges.foreach(e => e.update(min))
  }

  private def getMinResidualCapacity(current: Node): Int = {
    if(current == goal)
      return Integer.MAX_VALUE

    val child = current.child
    val edge = current.adjacencyList.find(e => e.terminalNode == child).get
    edges += edge
    val cap = edge.residualCapacity
    return math.min(cap, getMinResidualCapacity(child))
  }
}
