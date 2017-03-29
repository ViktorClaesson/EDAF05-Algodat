import util.Node

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source

/**
  * Created by Sebastian on 29/03/2017.
  */
object Builder {

  lazy val nodes10 = buildNodes(Files.WORDS_10)
  lazy val nodes50 = buildNodes(Files.WORDS_50)
  lazy val nodes250 = buildNodes(Files.WORDS_250)
  lazy val nodes500 = buildNodes(Files.WORDS_500)
  lazy val nodes5757 = buildNodes(Files.WORDS_5757)
  lazy val nodes104500 = buildNodes(Files.WORDS_104500)

  def buildNodes(path: String): Vector[Node] = {
    val nodes = Source.fromFile(path).getLines.map(x => new Node(x)).toVector
    val map: Map[String, ArrayBuffer[Node]] = Map.empty[String, ArrayBuffer[Node]]

    // We have a word, we get all possible substrings that are 1 character shorter & sorted.
    // Then we add them to the map, were there are two choices.
    //   1: Keyword doesn't exist, so we add a new entry mapping (keyword -> ArrayBuffer(word))
    //   2: Keyword does exist, so we get the ArrayBuffer for the keyword already in the map, and append to it the word.
    nodes.foreach(node => allOneShorterSubstrings(node.toString).foreach(key => addToMap(map, key, node)))
    nodes.foreach(node => node.setAdjacencyList(adjacencyList(map, node)))

    nodes
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

  def adjacencyList(map: Map[String, ArrayBuffer[Node]], node: Node): Vector[Node] = {
    map(lastFourCharactersSorted(node.toString)).filter(nodeX => !nodeX.eq(node)).toVector
  }
}

object Files{
  val WORDS_10: String = "words/words-10.txt"
  val WORDS_50: String = "words/words-50.txt"
  val WORDS_250: String = "words/words-250.txt"
  val WORDS_500: String = "words/words-500.txt"
  val WORDS_5757: String = "words/words-5757.txt"
  val WORDS_104500: String = "words/words-104500.txt"
}


