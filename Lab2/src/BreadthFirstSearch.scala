import scala.actors.remote.Node
import scala.collection.mutable.{ArrayBuffer, HashSet, Queue}
/**
  * Created by Sebastian on 22/03/2017.
  */
object BFS {

  def getShortestPath(root: Node, goal: Node): String ={

    var currentNode: Node = goal
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

  def getDistance(goal: Node): Int = {
    def rec(current: Node, i: Int): Int = {
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
  def build(root: Node, goal: Node): Node = {

    root.parent = null
    goal.parent = null
    val checked: HashSet[Node] = HashSet.empty[Node]
    val queue: Queue[Node] = Queue(root)

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

    null //goal not found
  }
}
