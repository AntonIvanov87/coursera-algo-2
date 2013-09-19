package ru.antonivanov87

import ru.antonivanov87.graph.{UnionFind, Edges, Edge}
import scala.annotation.tailrec

package object clustering {

  def getMaxSpacing(distances: Set[Edge])(numOfClusters: Int): Int = {

    val sortedEdges = distances.toList.sortBy(_.weight)

    @tailrec
    def getMSTAcc(
                   sortedEdges: List[Edge],
                   unionFind: UnionFind,
                   numOfClustersSoFar: Int,
                   MSTSoFar: Set[Edge]): Int = {

      sortedEdges match {
        case Nil => throw new IllegalArgumentException("too few clusters (" + numOfClusters + ")")
        case x::xs => {
          val edgeCreateCycle: Boolean = (unionFind getRoot x.node1) == (unionFind getRoot x.node2)
          if (edgeCreateCycle)
            getMSTAcc(xs, unionFind, numOfClustersSoFar, MSTSoFar)
          else
          if (numOfClustersSoFar == numOfClusters)
            x.weight
          else
          getMSTAcc(xs, unionFind.union(x.node1, x.node2), numOfClustersSoFar-1, MSTSoFar + x)
        }
      }

    }

    val nodes: Set[Int] = Edges getNodes distances
    getMSTAcc(sortedEdges, UnionFind(nodes), nodes.size, Set())

  }

}
