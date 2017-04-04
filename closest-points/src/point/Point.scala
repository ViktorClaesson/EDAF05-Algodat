package point

/**
  * Created by Sebastian on 04/04/2017.
  */
class Point (private val x_ : String, private val y_ : String){

  private val x = x_.toDouble
  private val y = y_.toDouble

  def getX() = x

  def getY() = y

  def dist(other: Point) = Math.sqrt(Math.pow((x - other.getX()), 2) + Math.pow(y - other.getY(), 2))

}
