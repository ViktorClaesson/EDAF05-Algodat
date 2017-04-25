package main

import point.Point

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Sebastian on 04/04/2017.
  */
object Algorithm {

  def closest_pair_set(points: Vector[Point]): (Point, Point) = {
    val pxs = points.sortBy(_.getX)
    val pys = points.sortBy(_.getY)
    closest_pair_rec_set(pxs, pys)
  }

  // OBS! P needs to be sorted with respect to X for this to work!
  private def closest_pair_rec_set(P: (Vector[Point], Vector[Point])): (Point, Point) = {
    // Base case, if we have three points it's easy enough to get the nearest points.
    if(P._1.size <= 3)
      return base_case(P._1)

    //Split points into a left and right side
    val split = P._1.splitAt(P._1.length / 2)
    val left_set = split._1.toSet
    val right_set = split._2.toSet
    val left = (split._1, P._2.filter(p => left_set.contains(p)))
    val right = (split._2, P._2.filter(p => right_set.contains(p)))

    // Get the min distance in the left side and min distance in the right side by recursion
    val left_min = closest_pair_rec_set(left)
    val right_min = closest_pair_rec_set(right)
    // Get the min pair of the two given by left/right
    val min_pair = Vector(left_min, right_min).minBy(s => s._1.dist(s._2))
    // Get the lowest distance at this point
    val delta = min_pair._1.dist(min_pair._2)

    // Get the x coordinate of the Line L (Which is the line in the middle)
    val Line_x = left._1.last.getX
    // Get all points in P (sorted by Y) that are within delta distance of L
    val S = P._2.filter(p => Math.abs(Line_x - p.getX) <= delta)

    // Combine the min pair from before with the min of the conquer algorithm,
    // then get the min of all the pairs
    (min_pair +: min_pairs(S)).minBy(s => s._1.dist(s._2))
  }

  def closest_pair(points: Vector[Point]): (Point, Point) = {
    val pxs = points.sortBy(_.getX)
    closest_pair_rec(pxs)
  }

  // OBS! P needs to be sorted with respect to X for this to work!
  private def closest_pair_rec(P: Vector[Point]): (Point, Point) = {
    // Base case, if we have three points it's easy enough to get the nearest points.
    if(P.size <= 3)
      return base_case(P)

    //Split points into a left and right side
    val split = P.splitAt(P.length / 2)
    val left = split._1
    val right = split._2

    // Get the min distance in the left side and min distance in the right side by recursion
    val left_min = closest_pair_rec(left)
    val right_min = closest_pair_rec(right)
    // Get the min pair of the two given by left/right
    val min_pair = Vector(left_min, right_min).minBy(s => s._1.dist(s._2))
    // Get the lowest distance at this point
    val delta = min_pair._1.dist(min_pair._2)

    // Get the x coordinate of the Line L (Which is the line in the middle)
    val Line_x = left.last.getX
    // Get all points in P (sorted by Y) that are within delta distance of L
    val S = P.filter(p => Math.abs(Line_x - p.getX) <= delta).sortBy(p => p.getY)

    // Combine the min pair from before with the min of the conquer algorithm,
    // then get the min of all the pairs
    (min_pair +: min_pairs(S)).minBy(s => s._1.dist(s._2))
  }

  /*
   - The algorithm described in the book for getting all the minimal pairs near to the line L
   */
  private def min_pairs(S: Vector[Point]): Vector[(Point, Point)] = {
    S.zipWithIndex.filter(s => s._2 != S.length - 1).map(s => closest_from_point(s._1, S.slice(s._2 + 1, s._2 + 16)))
  }

  /*
   - Returns the closest point pair from point -> one in points
   */
  private def closest_from_point(p: Point, points: Vector[Point]): (Point, Point) = {
    Vector.fill(points.length)(p).zip(points).minBy(s => s._1.dist(s._2))
  }

  /*
   - Get all possible subsets of length 2 = s
   - Then get the minimum in respect to distance between the points
   */
  private def base_case(P: Vector[Point]): (Point, Point) = {
    val v = P.toSet.subsets(2).map(s => s.toVector).minBy(v => v(0).dist(v(1))).toVector
    (v(0), v(1))
  }

  /*
   - Brute force attempt, too slow for big files.
   */
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
