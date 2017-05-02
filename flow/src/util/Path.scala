package util

/**
  * Created by Sebastian on 02/05/2017.
  */
class Path (root: Node, goal: Node){

  def getMinResidualCapacity(): Int ={
    getMin(root, goal)

    /*
    var currentNode = root
    var child = currentNode.child
    var min = currentNode.adjacencyList.find(e => e.terminalNode == child).get.residualCapacity

    while(currentNode != goal){
      child = currentNode.child
      val cap = child.adjacencyList.find(e => e.terminalNode == child).get.residualCapacity

      if(cap < min) min = cap
    }

    min
    */
  }

  def getMin(current: Node, goal: Node): Int = {
    if(current == goal)
      return Integer.MAX_VALUE

    val child = current.child
    val cap = current.adjacencyList.find(e => e.terminalNode == child).get.residualCapacity
    return math.min(cap, getMin(child, goal))
  }
}
