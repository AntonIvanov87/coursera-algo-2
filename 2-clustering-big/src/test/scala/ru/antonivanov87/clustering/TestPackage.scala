package ru.antonivanov87.clustering

import org.scalatest.FlatSpec
import ru.antonivanov87.graph.Edge

class TestPackage extends FlatSpec {

  "getNumOfClusters" should "return num. of clusters formed by folding all edges" in {

    val edge12: Edge = Edge(1, 2, weight=1)
    val edge13: Edge = Edge(1, 3, weight=1)
    val edge23: Edge = Edge(2, 3, weight=1)

    val edge45: Edge = Edge(4, 5, weight=1)

    val graph: Set[Edge] = Set(edge12, edge13, edge23, edge45)

    val maxNumOfClusters: Int = getNumOfClusters(graph)

    expectResult(2)(maxNumOfClusters)

  }

}
