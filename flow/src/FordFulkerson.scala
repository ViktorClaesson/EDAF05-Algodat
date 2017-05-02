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

    minimalCut(s).foreach(println)
  }

  def minimalCut(current: Node): Vector[Edge] = {
    val edges = current.adjacencyList.filter(e => e.residualCapacity != 0 && e.residualCapacity <= e.capacity)
    val maxed = for(e <- edges.filter(e => e.residualCapacity == e.capacity)) yield e
    maxed ++ (for(e <- edges.filterNot(e => e.residualCapacity == e.capacity)) yield minimalCut(e.terminalNode)).flatten
  }

}