package util

import scala.collection.mutable.{ArrayBuffer, HashSet, Queue}
/**
  * Created by Sebastian on 22/03/2017.
  */
object BFS {

  def getShortestPath(root: util.Node, goal: util.Node): String ={

    var currentNode: util.Node = goal
    val path = ArrayBuffer(goal)
    val sb = new StringBuilder()

    if(currentNode == null)
      return "N/A"

    while(currentNode.parent != null) {
      currentNode = currentNode.parent
      path += currentNode
    }

    path.reverse.mkString(" -> ")
  }

  def getDistance(goal: util.Node): Int = {
    def rec(current: util.Node, i: Int): Int = {
      if (current == null)
        return i
      else {
        rec(current.parent, i + 1)
      }
    }

  rec(goal, -1)
  }

  //returns the distance between root and goal, and the goal node itself that contains
  //the start of the path to root
  def build(root: util.Node, goal: util.Node): Boolean = {

    root.parent = null
    goal.parent = null
    val checked: HashSet[util.Node] = HashSet(root)
    val queue: Queue[util.Node] = Queue(root)

    while(!queue.isEmpty){
      val currentNode = queue.dequeue()

      if(currentNode == goal)
        return true

      for(e <- currentNode.getAdjacencyList; if !checked.contains(e.terminalNode); e.residualCapacity != 0){
        checked.add(e.terminalNode)
        e.terminalNode.parent = currentNode
        currentNode.child = e.terminalNode
        queue.enqueue(e.terminalNode)
      }
    }

    false //goal not found
  }
}
