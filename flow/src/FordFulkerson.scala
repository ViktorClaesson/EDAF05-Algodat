import util.{BFS, Node, Path}

/**
  * Created by Sebastian on 02/05/2017.
  */
class FordFulkerson {

  def augment(): Unit ={
    //val b = bottleneck()
  }

  def bottleneck(s: Node, t: Node): Int = {
    BFS.build(s, t)
    val path = new Path(s, t)
    path.getMinResidualCapacity()
  }

}
