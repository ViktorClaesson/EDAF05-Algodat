import util.{BFS, Node, Path}

/**
  * Created by Sebastian on 02/05/2017.
  */
object FordFulkerson {

  def run(s: Node, t: Node): Unit ={
    while(BFS.build(s, t)) {
      val path = new Path(s, t)
      path.updateResidualCapacity()
    }
  }

}