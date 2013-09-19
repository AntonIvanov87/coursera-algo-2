package ru.antonivanov87.clustering

import org.scalatest.FlatSpec
import scala.io.Source
import ru.antonivanov87.graph.Edge

class TestNodes extends FlatSpec {

  val nodes: Vector[Node] = {
    val source: Source = Source fromURL (getClass getResource "/clustering_example.txt")
    (Nodes fromSource source).toVector
  }

  "fromSource" should "read 3 nodes" in {

    expectResult(3)(nodes.length)

  }

  it should "read 111000001101001111001111 as first node" in {

    expectResult(24)(nodes.head.bits.length)
    expectResult(
      Node(1, true::true::true::false::false::false::false::false::true::true::false::true::false::false::true::true::true::true::false::false::true::true::true::true::Nil)
    )(nodes.head)

  }

  it should "read 101111100110000100001110 as last node" in {

    expectResult(24)(nodes.last.bits.length)
    expectResult(
      Node(3, true::false::true::true::true::true::true::false::false::true::true::false::false::false::false::true::false::false::false::false::true::true::true::false::Nil)
    )(nodes.last)

  }

  "toEdges" should "return edges constructed from nodes with smaller than maxDistance distance" in {

    val node1: Node = Node(1, true::true::true::Nil)
    val node2: Node = Node(2, true::true::true::Nil)
    val node3: Node = Node(3, true::true::false::Nil)
    val node4: Node = Node(4, false::false::false::Nil)
    val nodes: List[Node] = List(node1, node2, node3, node4)

    val edges: Set[Edge] = Nodes.toEdges(nodes, 1).toSet

    val expectedEdge12: Edge = Edge(1, 2, weight=0)
    val expectedEdge13: Edge = Edge(1, 3, weight=1)
    val expectedEdge23: Edge = Edge(2, 3, weight=1)
    expectResult(3)(edges.size)
    assert(edges contains expectedEdge12)
    assert(edges contains expectedEdge13)
    assert(edges contains expectedEdge23)

  }

}
