/**
  * Created by Sebastian on 22/03/2017.
  */
class Node (val name: String) {

  var adjacencyList: Vector[Node] = Vector.empty[Node]

  def setAdjacencyString(list: Vector[Node]): Unit = adjacencyList = list

  override def toString: String = name

  def adjacencyString: String = toString + " -> (" + adjacencyList.mkString(", ") + ")"
}

