/**
  * Created by viktor on 2017-03-27.
  */

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    // time0 is for measuring the time it takes to perform the building of tree.
    var time0: Long = 0
    time0 = System.currentTimeMillis
    val nodes = Source.fromFile("data/words-50.txt").getLines.map(x => new Node(x)).toVector
    println("Reading file: " + (System.currentTimeMillis - time0) + " ms")
    val map: Map[String, ArrayBuffer[Node]] = Map.empty[String, ArrayBuffer[Node]]

    // We have a word, we get all possible substrings that are 1 character shorter & sorted.
    // Then we add them to the map, were there are two choices.
    //   1: Keyword doesn't exist, so we add a new entry mapping (keyword -> ArrayBuffer(word))
    //   2: Keyword does exist, so we get the ArrayBuffer for the keyword already in the map, and append to it the word.
    time0 = System.currentTimeMillis
    nodes.foreach(node => allOneShorterSubstrings(node.toString).foreach(key => addToMap(map, key, node)))
    println("Build map: " + (System.currentTimeMillis - time0) + " ms")
    time0 = System.currentTimeMillis
    nodes.foreach(node => node.setAdjacencyString(t4(map, node)))
    println("Set adjacency: " + (System.currentTimeMillis - time0) + " ms")

    // Printing the list, mostly for checking validity
    time0 = System.currentTimeMillis
    nodes.foreach(n => println(n.adjacencyString))
    println("Printing List: " + (System.currentTimeMillis - time0) + " ms")
  }

  def allOneShorterSubstrings(arg: String): Set[String] = {
    arg.zipWithIndex.map(c => arg.take(arg.length - 1 - c._2).concat(arg.takeRight(c._2)).sorted).toSet
  }

  def addToMap(map: Map[String, ArrayBuffer[Node]], keyWord: String, node: Node): Unit = {
    if(map.contains(keyWord))
      map(keyWord).append(node)
    else
      map += ((keyWord, ArrayBuffer(node)))
  }

  def lastFourCharactersSorted(arg: String): String = {
    arg.takeRight(4).sorted
  }

  def t4(map: Map[String, ArrayBuffer[Node]], node: Node): Vector[Node] = {
    map(lastFourCharactersSorted(node.toString)).filter(nodeX => !nodeX.eq(node)).toVector
  }
}