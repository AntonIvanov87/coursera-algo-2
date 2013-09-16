package ru.antonivanov87.graph

import org.scalatest.FlatSpec
import scala.io.Source

class TestEdges extends FlatSpec {

  val edges: Vector[Edge] = {
    val source: Source = Source fromURL (getClass getResource "/edgesExample.txt")
    (Edges fromSource source).toVector
  }

  "fromSource" should "return 3 edges" in {

    expectResult(3)(edges.length)

  }

  it should "read ru.antonivanov87.prim.Edge(node1=1, node2=2, weight=6808) as first edge" in {

    expectResult(Edge(node1=1, node2=2, weight=6808))(edges.head)

  }

  it should "read ru.antonivanov87.prim.Edge(node1=499, node2=500, weight=8273) as last edge" in {

    expectResult(Edge(node1=499, node2=500, weight=8273))(edges.last)

  }

  "getNodes" should "return empty set for empty graph" in {

    val nodes: Set[Int] = Edges getNodes Set()

    expectResult(Set())(nodes)

  }

  it should "return set of unique nodes" in {

    val nodes: Set[Int] = Edges getNodes edges.toSet

    expectResult(Set(1, 2, 499, 500))(nodes)

  }

  "getSumOfWeights" should "return 0 for empty graph" in {

    val actualSumOfWeights = Edges getSumOfWeights Set()

    expectResult(0)(actualSumOfWeights)

  }

  it should "return sum of weights of edges" in {
    val edge1 = Edge(node1=1, node2=2, weight=1)
    val edge2 = Edge(node1=2, node2=3, weight=2)
    val graph = Set(edge1, edge2)

    val actualSumOfWeights = Edges getSumOfWeights graph

    expectResult(3)(actualSumOfWeights)

  }

}
