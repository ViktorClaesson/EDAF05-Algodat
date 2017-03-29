package util

/**
  * Created by Sebastian on 22/03/2017.
  */
class Node (val name: String) {

  var adjacencyList: Vector[Node] = Vector.empty[Node]
  var parent: Node = null

  def setAdjacencyList(list: Vector[Node]): Unit = adjacencyList = list

  def getAdjacencyList = adjacencyList

  override def toString: String = name

  def adjacencyString: String = toString + " -> (" + adjacencyList.mkString(", ") + ")"

   override def equals(o: Any) = o match {
    case that: Node => that.name == name
    case _ => false
  }

  def getCopy: Node = {
    val n = new Node(name)
    n.setAdjacencyList(adjacencyList)

    n
  }
}

