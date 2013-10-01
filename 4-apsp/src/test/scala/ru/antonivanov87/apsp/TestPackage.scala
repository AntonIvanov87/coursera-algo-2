package ru.antonivanov87.apsp

import org.scalatest.FlatSpec
import ru.antonivanov87.graph.Edge

class TestPackage extends FlatSpec {

  val graphAllEdgesPositive: Set[Edge] = {
    val edge12: Edge = Edge(1, 2, weight = 1)
    val edge13: Edge = Edge(1, 3, weight = 3)
    val edge23: Edge = Edge(2, 3, weight = 1)
    Set(edge12, edge13, edge23)
  }

  val graphSomeEdgesNegative: Set[Edge] = {
    val edge12: Edge = Edge(1, 2, weight = 1)
    val edge23: Edge = Edge(2, 3, weight = -2)
    val edge13: Edge = Edge(3, 1, weight = 1)
    Set(edge12, edge13, edge23)
  }

  val graphNegativeCycle: Set[Edge] = {
    val edge12: Edge = Edge(1, 2, weight = -1)
    val edge13: Edge = Edge(2, 3, weight = -2)
    val edge23: Edge = Edge(3, 1, weight = -3)
    Set(edge12, edge13, edge23)
  }

  "getMinPathsLengths" should "return 0 when all edges have positive lengths" in {

    assertResult(0)(getMinPathLength(graphAllEdgesPositive))

  }

  it should "return Int.MinValue when graph has negative cycle" in {

  }

  "getPathsLengths" should "correctly compute paths lengths when all edges have positive lengths" in {

    val actualPathsLengths: Vector[Vector[Int]] = getPathsLengths(graphAllEdgesPositive)

    assertResult(1)(actualPathsLengths(1)(2))
    assertResult(2)(actualPathsLengths(1)(3))
    assertResult(1)(actualPathsLengths(2)(3))
    assertResult(0)(actualPathsLengths(1)(1))
    assertResult(0)(actualPathsLengths(2)(2))
    assertResult(0)(actualPathsLengths(3)(3))
    assertResult(Int.MaxValue)(actualPathsLengths(2)(1))

  }

  it should "correctly compute paths lengths when some edges have negative lengths" in {

    val actualPathsLengths: Vector[Vector[Int]] = getPathsLengths(graphSomeEdgesNegative)

    assertResult(1)(actualPathsLengths(1)(2))
    assertResult(-2)(actualPathsLengths(2)(3))
    assertResult(1)(actualPathsLengths(3)(1))
    assertResult(-1)(actualPathsLengths(1)(3))
    assertResult(-1)(actualPathsLengths(2)(1))
    assertResult(2)(actualPathsLengths(3)(2))
    assertResult(0)(actualPathsLengths(1)(1))
    assertResult(0)(actualPathsLengths(2)(2))
    assertResult(0)(actualPathsLengths(3)(3))

  }

  it should "return empty Vector if graph has negative cycle" in {

    assertResult(Vector.empty)(getPathsLengths(graphNegativeCycle))

  }

  "initNodesToWeights" should "return initial map of nodes to weights" in {

    val actualNodesToWeights: Vector[Vector[Int]] = initNodesToWeights(graphAllEdgesPositive, maxNodeIndex=3)

    assertResult(1)(actualNodesToWeights(1)(2))
    assertResult(1)(actualNodesToWeights(2)(3))
    assertResult(3)(actualNodesToWeights(1)(3))
    assertResult(0)(actualNodesToWeights(1)(1))
    assertResult(0)(actualNodesToWeights(2)(2))
    assertResult(0)(actualNodesToWeights(3)(3))

  }

  it should "return Int.MaxValue if there is no such node" in {

    val edge: Edge = Edge(1, 2, weight = 1)
    val nodesToWeights: Vector[Vector[Int]] = initNodesToWeights(Set(edge), 2)
    assertResult(Int.MaxValue)(nodesToWeights(2)(1))

  }

  "getNodes" should "return set of nodes" in {

    assertResult(Set(1, 2, 3))(getNodes(graphAllEdgesPositive))

  }

  it should "return empty set when graph is empty" in {

    assertResult(Set())(getNodes(Set()))

  }

}
