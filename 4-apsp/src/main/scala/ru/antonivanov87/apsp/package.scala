package ru.antonivanov87

import ru.antonivanov87.graph.Edge
import scala.annotation.tailrec

package object apsp {

  def getMinPathLength(graph: Set[Edge]): Int = {

    val pathsLengths: Vector[Vector[Int]] = getPathsLengths(graph)
    if (pathsLengths.isEmpty) {
      Int.MinValue
    } else {
      pathsLengths.map(_.min).min
    }

  }

  private[apsp] def getPathsLengths(graph: Set[Edge]): Vector[Vector[Int]] = {

    if (graph.isEmpty) {
      return Vector.empty
    }

    val nodes: Set[Int] = getNodes(graph)

    @tailrec
    def getNextPathsLengths(restNodes: Set[Int], nodesToWeights: Vector[Vector[Int]]): Vector[Vector[Int]] = {

      println(restNodes.size + " nodes left")

      if (restNodes.isEmpty) {
        if (hasNegativeCycle(nodesToWeights))
          Vector()
        else
          nodesToWeights

      } else {
        val interNode: Int = restNodes.head
        var newNodesToWeights: Vector[Vector[Int]] = nodesToWeights
        for(i: Int <- nodes; j: Int <- nodes) {

            val notUsingInterNode: Int = nodesToWeights(i)(j)
            val minLength =
              if (nodesToWeights(i)(interNode) == Int.MaxValue || nodesToWeights(interNode)(j) == Int.MaxValue) {
                notUsingInterNode
               } else {
                 val usingK: Int = nodesToWeights(i)(interNode) + nodesToWeights(interNode)(j)
                 math.min(notUsingInterNode, usingK)
              }
            newNodesToWeights = newNodesToWeights.updated(i, newNodesToWeights(i).updated(j, minLength))

        }

        getNextPathsLengths(restNodes.tail, newNodesToWeights)

      }

    }

    val nodesToWeights: Vector[Vector[Int]] = initNodesToWeights(graph, nodes.max)
    getNextPathsLengths(nodes, nodesToWeights)

  }

  private[apsp] def getNodes(graph: Set[Edge]): Set[Int] = {

    graph.flatMap(edge => List(edge.node1, edge.node2)).toSet

  }

  private[apsp] def initNodesToWeights(edges: Set[Edge], maxNodeIndex: Int): Vector[Vector[Int]] = {

    // XXX: fold is slower?
    var nodesToWeights: Vector[Vector[Int]] =
      (for(i <- 0 to maxNodeIndex) yield {
        (for(j <- 0 to maxNodeIndex) yield {
          if (i==j) 0 else Int.MaxValue
        }).toVector
      }).toVector

    for (edge: Edge <- edges) {
      val inner: Vector[Int] = nodesToWeights(edge.node1).updated[Int, Vector[Int]](edge.node2, edge.weight)
      nodesToWeights = nodesToWeights.updated(edge.node1, inner)
    }

    nodesToWeights

  }

  private[apsp] def hasNegativeCycle(nodesToLengths: Vector[Vector[Int]]): Boolean = {

    for (i <- 0 until nodesToLengths.size) {
      if (nodesToLengths(i)(i) < 0) {
        return true
      }
    }

    false

  }

}
