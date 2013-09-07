package ru.antonivanov87.prim

import org.scalatest.FlatSpec
import scala.io.Source

class TestPackage extends FlatSpec {

  "mst" should "return minimum spanning tree" in {

    val edge12: Edge = Edge(node1=1, node2=2, weight=1)
    val edge23: Edge = Edge(node1=2, node2=3, weight=2)
    val edge34: Edge = Edge(node1=3, node2=4, weight=3)
    val edge14: Edge = Edge(node1=1, node2=4, weight=4)
    val edge13: Edge = Edge(node1=1, node2=3, weight=5)
    val edge24: Edge = Edge(node1=2, node2=4, weight=6)
    val graph: Set[Edge] = Set(edge12, edge23, edge34, edge14, edge13, edge24)

    expectResult(Set(edge12, edge23, edge34))(MST(graph))

  }

  "edgeCrossesFront" should "return true if edge crosses front" in {

    val edge12: Edge = Edge(node1=1, node2=2, weight=1)
    val nodesInMST: Set[Int] = Set(1)

    expectResult(true)(edgeCrossesFront(nodesInMST)(edge12))

  }

  "edgeCrossesFront" should "return false if edge lies in front" in {

    val edge12: Edge = Edge(node1=1, node2=2, weight=1)
    val nodesInMST: Set[Int] = Set(1, 2)

    expectResult(false)(edgeCrossesFront(nodesInMST)(edge12))

  }

  "edgeCrossesFront" should "return false if edge lies out of front" in {

    val edge12: Edge = Edge(node1=1, node2=2, weight=1)
    val nodesInMST: Set[Int] = Set()

    expectResult(false)(edgeCrossesFront(nodesInMST)(edge12))

  }

  "sumOfEdges" should "return sum of weights of edges" in {

    val edge12: Edge = Edge(node1=1, node2=2, weight=1)
    val edge13: Edge = Edge(node1=1, node2=3, weight=2)
    val graph: Set[Edge] = Set(edge12, edge13)

    expectResult(3)(sumOfEdges(graph))

  }

  "edges.txt" should "contain MST with sum of edges -3612829" in {

    val source: Source = Source fromURL (getClass getResource "/edges.txt")
    val graph: Set[Edge] = EdgesFromFile.read(source).toSet
    val graphMST: Set[Edge] = MST(graph)
    val primSumOfEdges: Int = sumOfEdges(graphMST)
    expectResult(-3612829)(primSumOfEdges)

  }

}
