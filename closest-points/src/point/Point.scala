package point

/**
  * Created by Sebastian on 04/04/2017.
  */
class Point (private val x: String, private val y: String){

  private val xd = x.toDouble
  private val yd = y.toDouble

  def getX() = xd

  def getY() = yd

  def dist(other: Point) = Math.sqrt(Math.pow((xd - other.getX()), 2) + Math.pow(yd-other.getY(), 2))

}
