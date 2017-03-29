package graphics

import util.Node

import scala.collection.mutable.{HashSet, Queue}
/**
  * Created by Sebastian on 29/03/2017.
  */
class Graph (node: Node){

  /**
    * Create a BFS graph with rooted in root node. Marks the goal node in red.
    * @param root
    * @param goal
    */
  def createBFSTree(root: Node, goal: Node): Unit = {

  }

  private def BFSbuilder(root: Node, goal: Node): Unit ={

      root.parent = null
      goal.parent = null
      val checked: HashSet[util.Node] = HashSet(root)
      val queue: Queue[util.Node] = Queue(root)

      while(!queue.isEmpty){
        val currentNode = queue.dequeue()

        if(currentNode == goal)
          return goal

        for(n <- currentNode.getAdjacencyList; if !checked.contains(n)){
          checked.add(n)
          n.parent = currentNode
          queue.enqueue(n)
        }

  }
}
