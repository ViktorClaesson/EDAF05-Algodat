import util.{BFS, Node, Path}

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Queue

/**
  * Created by Sebastian on 02/05/2017.
  */
object FordFulkerson {

  def run(s: Node, t: Node): Unit ={
    while(BFS.build(s, t)) {                                            // While it finds a path from start to end
      val path = new Path(s, t)                                         // Create path from start to end
      path.updateResidualCapacity()                                     // Update all the residual capacity
    }                                                                   // if done it can't create another path from start to end

    val minCut = minimalCut(s)                                          // Creates Vector[Node] consisting of all the Nodes that are part of the minimal cut
    val edgeEdges = minCut.flatMap(n => n.adjacencyList).distinct       // Adds all the edges from the nodes to a vector
      .filter(e => e.residualCapacity == 0)                             // Filter only the ones with max flow
      .filterNot(e => minCut.contains(e.terminalNode))                  // Filter only the ones with other part of the edge not in the mincut set
    edgeEdges.sortBy(_.startingNode.index).foreach(println)             // Just sorting for nicer output and printing all the edges
    printf("Sum: %d\n", edgeEdges.map(e => e.capacity).sum)             // Prints the sum
  }

  // Returns a Vector of all the nodes that are in the minimal cut
  def minimalCut(root: Node): Vector[Node] = {
    val visitedNodes = ArrayBuffer(root)                                // Creates a buffer of all visited nodes (beginning with root)
    val queue = Queue(root)                                             // Creates a queue with nodes that we visit (beginning with root)

    do {
      val current = queue.dequeue()                                     // Gets the first node in the queue
      val edges = current.adjacencyList                                 // Get all the edges from the node
        .filterNot(e => e.residualCapacity == 0)                        // Filter out the ones with max flow
        .filterNot(e => visitedNodes.contains(e.terminalNode))          // Filter out already visited nodes
      visitedNodes ++= edges.map(e => e.terminalNode)                   // Add nodes to visited
      edges.foreach(e => queue.enqueue(e.terminalNode))                 // Add nodes to queue
    } while (!queue.isEmpty)

    visitedNodes.toVector                                               // Return the visited nodes aka the minimal cut
  }
}