package ru.antonivanov87.clustering

import scala.io.Source
import ru.antonivanov87.graph.{Edges, Edge}

object Main {

  def main(args: Array[String]) = {

    val source: Source = Source fromURL (getClass getResource "/clustering_big.txt")
    val nodes: List[Node] = Nodes fromSource source
    val numOfNodes: Int = nodes.length
    print("Num of nodes: " + numOfNodes)

    val smallEdges: Set[Edge] = Nodes.toEdges(nodes, 2).toSet
    print("Num of edges with distance <= 2: " + smallEdges.size)

    val nodesInSmallEdges: Set[Int] = Edges getNodes smallEdges
    print("Num of nodes in edges with distance <= 2: " + nodesInSmallEdges.size)

    val numOfSmallClusters: Int = getNumOfClusters(smallEdges)
    print("Num of clusters formed by edges with distance <= 2: " + numOfSmallClusters)

    val numOfClusters: Int = numOfNodes - nodesInSmallEdges.size + numOfSmallClusters
    print("Num of clusters: " + numOfClusters)

  }

}
