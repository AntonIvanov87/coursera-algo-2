package ru.antonivanov87

import scala.annotation.tailrec
import ru.antonivanov87.graph.Edge

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

  private[prim] def edgeCrossesFront(nodesInMST: Set[Int])(edge: Edge) =
    nodesInMST.contains(edge.node1) && !nodesInMST.contains(edge.node2) ||
      nodesInMST.contains(edge.node2) && !nodesInMST.contains(edge.node1)

}
