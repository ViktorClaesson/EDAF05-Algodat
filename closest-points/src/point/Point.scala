package point

import java.lang.Math.sqrt
import java.lang.Math.pow

/**
  * Created by Sebastian on 04/04/2017.
  */
case class Point (val name: String, private val x_ : String, private val y_ : String){

  private val x: Double = x_.toDouble
  private val y: Double = y_.toDouble

  def getX: Double = x

  def getY: Double = y

  def dist(other: Point) = sqrt(pow(x - other.getX, 2) + pow(y - other.getY, 2))
}
