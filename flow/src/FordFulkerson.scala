import util.{BFS, Node, Path}

/**
  * Created by Sebastian on 02/05/2017.
  */
class FordFulkerson {

  def augment(s: Node, t: Node): Unit ={
    val path: Path = new Path(s,t)
    BFS.build(s,t)

    val b = path.getMinResidualCapacity()
  }

}