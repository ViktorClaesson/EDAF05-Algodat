package main

import point.Point

/**
  * Created by Sebastian on 04/04/2017.
  */
object Algorithm {


  def closest_pair(points: Vector[Point]): (Point, Point) = {
    val pxs = points.sortBy(_.getX)
    val pys = points.sortBy(_.getY)

  }

  def closest_pair_rec(P_x: Vector[Point], P_y: Vector[Point]): (Point, Point) = {
    if(P_x.size <= 3)
      return base_case(P_x)

    //Split points in middle based on x coordinate
    val split = P_x.splitAt(P_x.length / 2)
    val leftx = split._1
    val lefty = leftx.sortBy(_.getY)
    val rightx = split._2
    val righty = rightx.sortBy(_.getY)

    val (q0, q1) = closest_pair_rec(leftx, lefty)
    val (r0, r1) = closest_pair_rec(rightx, righty)

    val delta = math.min(q0.dist(q1), r0.dist(r1));
    val L_x = leftx.last  // x position of the Line L
    val S_y = P_y.filter(p => Math.abs(L_x - p.getX) <= delta) // All points in P that are within delta distance of L

    

    for(i <- 0 to S_y.length - 16) {
      val p = S_y(i)
      val (p1, p2, dist) = min_distance_to_point(p, S_y.slice(i + 1, i + 16))

    }
  }

  def base_case(P: Vector[Point]): (Point, Point) = {
     ???
  }

  def min_distance_to_point(p: Point, points: Vector[Point]): (Point, Point, Double) = {
    var dist = Double.MaxValue
    var closest: Point = null

    points.foreach(p_ => {
      val dist_ = p_.dist(p)
      if (dist_ < dist) {
        dist = dist_
        closest = p_
      }
    })

    (p, closest, dist)
  }

  def brute_force(points: Vector[Point]): (Point, Point) = {
    var pair: (Point, Point) = (null, null)
    var lowestDist = Double.MaxValue
    points.foreach(p1 => points.foreach(p2 => {
      if(p1 != p2) {
        val dist = p1.dist(p2)
        if (dist < lowestDist) {
          pair = (p1, p2)
          lowestDist = dist
        }
      }
    }))
    pair
  }
}
