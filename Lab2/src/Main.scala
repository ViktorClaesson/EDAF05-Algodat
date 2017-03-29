/**
  * Created by viktor on 2017-03-27.
  */

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.{Source, StdIn}
import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    val nodes10 = buildNodes(Files.WORDS_10)
    val nodes50 = buildNodes(Files.WORDS_50)
    val nodes250 = buildNodes(Files.WORDS_250)
    val nodes500 = buildNodes(Files.WORDS_500)
    val nodes5757 = buildNodes(Files.WORDS_5757)
    val nodes104500 = buildNodes(Files.WORDS_104500)
    // Printing the list, mostly for checking validity
    //nodes10.foreach(n => println(n.adjacencyString))

    val tester = new Tester()
//    tester.run10(nodes10)
//    tester.run50(nodes50)
//    tester.run250(nodes250)
      tester.run500(nodes500)
//    tester.run5757(nodes5757)
//    tester.run104500(nodes104500)

    //Parser.run(nodes10)
  }

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

object Parser{

  def run(nodes: Vector[Node]): Unit = {

    while(true){
      readInput(nodes)
    }
  }

  def readInput(nodes: Vector[Node]): Unit = {
    val rootStr = StdIn.readLine("Input root node: ")
    val goalStr = StdIn.readLine("Input goal node: ")

    val root = nodes.find(n => n.name == rootStr)
    val goal = nodes.find(n => n.name == goalStr)
    var bfsNode: Node = null

    if (root.isEmpty || goal.isEmpty) {
      println("Goal or Root does not exist")
      return
    }
    else {
      bfsNode = BFS.build(root.get, goal.get)
    }

    println("-" * 30)
    println("Distance " + root.get + " -> " + goal.get + " = " + BFS.getDistance(bfsNode))
    println("Shortest path: " + BFS.getShortestPath(root.get, bfsNode))
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