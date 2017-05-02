package util

/**
  * Created by Sebastian on 22/03/2017.
  */
class Node (val name: String, val index: Int) {

  var adjacencyList: Vector[Edge] = Vector.empty[Edge]
  var parent: Node = null

  def setAdjacencyList(list: Vector[Edge]): Unit = adjacencyList = list

  def getAdjacencyList = adjacencyList

  def addEdge(edge: Edge): Unit ={
    adjacencyList = adjacencyList :+ edge
  }

  override def toString: String = s"$name ($index)"

  def adjacencyString: String = toString + " -> (" + adjacencyList.mkString(", ") + ")"

   override def equals(o: Any) = o match {
    case that: Node => that.name == name
    case _ => false
  }

  def getCopy: Node = {
    val n = new Node(name, index)
    n.setAdjacencyList(adjacencyList)

    n
  }
}

