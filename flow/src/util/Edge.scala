package util

/**
  * Created by Sebastian on 02/05/2017.
  */
class Edge (val startingNode: Node, val terminalNode: Node, val capacity: Int){

  var residualCapacity: Int = capacity

  var siblingEdge: Edge = null

  override def toString: String = s"[$capacity]- ${34.toChar}$terminalNode${34.toChar}"

  def update(min: Int) = {
    residualCapacity -= min
    siblingEdge.residualCapacity += min
  }
}
