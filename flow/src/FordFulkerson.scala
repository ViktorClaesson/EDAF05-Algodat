import util.{BFS, Edge, Node, Path}

/**
  * Created by Sebastian on 02/05/2017.
  */
object FordFulkerson {

  def run(s: Node, t: Node): Unit ={
    while(BFS.build(s, t)) {
      val path = new Path(s, t)
      path.updateResidualCapacity()
    }

    minimalCut(null, s).foreach(println)
  }

  def minimalCut(prev: Node, current: Node): Vector[Edge] = {
    val edges = current.adjacencyList.filter(e => e.residualCapacity < e.capacity || (e.capacity == -1 && e.terminalNode != prev))
    val maxed = for(e <- edges.filter(e => e.residualCapacity == 0)) yield e
    maxed ++ (for(e <- edges.filterNot(e => e.residualCapacity == 0)) yield minimalCut(current, e.terminalNode)).flatten
  }

}