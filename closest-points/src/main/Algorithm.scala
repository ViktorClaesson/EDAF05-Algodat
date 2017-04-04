package main

import point.Point

/**
  * Created by Sebastian on 04/04/2017.
  */
object Algorithm {


  def closest_pair(points: Vector[Point]): (Point, Point) = {
    val pxs = points.sortBy(_.getX)
    val pys = points.sortBy(_.getY)

    return closest_pair_rec(pxs, pys)
  }

  private def closest_pair_rec(P_x: Vector[Point], P_y: Vector[Point]): (Point, Point) = {
    if(P_x.size <= 3)
      return base_case(P_x)

    //Split points in middle based on x coordinate
    val split = P_x.splitAt(P_x.length / 2)
    val leftx = split._1
    val lefty = leftx.sortBy(_.getY)
    val rightx = split._2
    val righty = rightx.sortBy(_.getY)

    val (q0, q1) = closest_pair_rec(leftx, lefty)
    val q_dist = q0.dist(q1)
    val (r0, r1) = closest_pair_rec(rightx, righty)
    val r_dist = r0.dist(r1)
    var min_pair: (Point, Point) = (null, null)
    var delta = Double.MaxValue

    if(q_dist < r_dist) {
      min_pair = (q0, q1)
      delta = q_dist
    } else {
      min_pair = (r0, r1)
      delta = r_dist
    }

    val L_x = leftx.last.getX  // x position of the Line L
    val S_y = P_y.filter(p => Math.abs(L_x - p.getX) <= delta) // All points in P that are within delta distance of L

    for(i <- 0 until S_y.length) {
      val p = S_y(i)
      val (p1, p2, dist) = min_distance_to_point(p, S_y.slice(i + 1, i + 16))
      if(dist < delta) {
        delta = dist
        min_pair = (p1, p2)
      }
    }

    return min_pair
  }

  private def base_case(P: Vector[Point]): (Point, Point) = {
    val v = P.toSet.subsets(2).toVector.minBy(s => {val v = s.toVector; v(0).dist(v(1))}).toVector
    (v(0), v(1))
  }

  private def min_distance_to_point(p: Point, points: Vector[Point]): (Point, Point, Double) = {
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
