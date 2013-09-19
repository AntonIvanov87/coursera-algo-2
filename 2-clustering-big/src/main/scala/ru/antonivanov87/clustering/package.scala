package ru.antonivanov87

import ru.antonivanov87.graph.{Edges, UnionFind, Edge}
import scala.annotation.tailrec

package object clustering {

  def getNumOfClusters(graph: Set[Edge]): Int = {

    @tailrec
    def getNumOfClustersAcc(
                   edges: List[Edge],
                   unionFind: UnionFind,
                   numOfClustersSoFar: Int): Int = {

      edges match {
        case Nil => numOfClustersSoFar
        case x::xs => {
          val edgeCreateCycle: Boolean = (unionFind getRoot x.node1) == (unionFind getRoot x.node2)
          if (edgeCreateCycle)
            getNumOfClustersAcc(xs, unionFind, numOfClustersSoFar)
          else
            getNumOfClustersAcc(xs, unionFind.union(x.node1, x.node2), numOfClustersSoFar-1)
        }
      }

    }

    val nodes: Set[Int] = Edges getNodes graph
    getNumOfClustersAcc(graph.toList, UnionFind(nodes), nodes.size)

  }

}
