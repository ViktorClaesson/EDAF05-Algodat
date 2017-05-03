package util

/**
  * Created by Sebastian on 02/05/2017.
  */
class Edge (val startingNode: Node, val terminalNode: Node, val capacity: Int){

  var residualCapacity: Int = capacity
  var siblingEdge: Edge = null

  override def toString: String = f"$startingNode%2s $terminalNode%2s $capacity%2d $residualCapacity%2d"

  def update(min: Int) = {
    if(capacity != -1) {
      residualCapacity -= min
      siblingEdge.residualCapacity += min
    }
  }
}
