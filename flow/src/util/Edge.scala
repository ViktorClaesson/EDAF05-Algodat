package util

/**
  * Created by Sebastian on 02/05/2017.
  */
class Edge (val terminalNode: Node, capacity: Int){

  override def toString: String = s"[$capacity]- ${34.toChar}$terminalNode${34.toChar}"
}
