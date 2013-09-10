package ru.antonivanov87

import scala.annotation.tailrec

package object prim {

  def MST(graph: Set[Edge]): Set[Edge] = {

    @tailrec
    def primAcc(nodesInMST: Set[Int], edgesInMST: Set[Edge]): Set[Edge] = {

      val edgesThatCrossesFront: Set[Edge] = graph.filter(edgeCrossesFront(nodesInMST))
      if (edgesThatCrossesFront.isEmpty)
        edgesInMST
      else {
        val minEdgeThatCrossesFront: Edge = edgesThatCrossesFront.reduce(
          (edge1: Edge, edge2: Edge) => if (edge1.weight < edge2.weight) edge1 else edge2
        )
        val newNodesInMST: Set[Int] = nodesInMST + (minEdgeThatCrossesFront.node1, minEdgeThatCrossesFront.node2)
        val newEdgesInMST: Set[Edge] = edgesInMST + minEdgeThatCrossesFront
        primAcc(newNodesInMST, newEdgesInMST)
      }

    }

    primAcc(Set(graph.head.node1), Set())

  }

  private[prim] def edges2nodes(edges: Set[Edge]): Map[Int, Set[Edge]] = {

      val emptyNodes: Map[Int, Set[Edge]] = Map().withDefaultValue(Set[Edge]())

      def updateNodes(nodes: Map[Int, Set[Edge]], edge: Edge): Map[Int, Set[Edge]] =
        nodes + (edge.node1 -> (nodes(edge.node1) + edge),
          edge.node2 -> (nodes(edge.node2) + edge))

      edges.foldLeft(emptyNodes)(updateNodes)

  }

  private[prim] def edgeCrossesFront(nodesInMST: Set[Int])(edge: Edge) =
    nodesInMST.contains(edge.node1) && !nodesInMST.contains(edge.node2) ||
      nodesInMST.contains(edge.node2) && !nodesInMST.contains(edge.node1)

  def sumOfEdges(graph: Set[Edge]): Int =
    graph.foldLeft[Int](0)(_ + _.weight)

}
