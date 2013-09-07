package ru.antonivanov87.prim

import scala.io.Source
import org.scalatest.FlatSpec

class TestEdgesFromFile extends FlatSpec {

  val source: Source = Source.fromURL (getClass getResource "/edges.txt")
  val edges: Vector[Edge] = EdgesFromFile.read(source).toVector

  it should "read 2184 graph" in {

    expectResult(2184)(edges.length)

  }

  it should "read ru.antonivanov87.prim.Edge(node1=1, node2=2, weight=6807) as first edge" in {

    expectResult(Edge(node1=1, node2=2, weight=6807))(edges.head)

  }

  it should "read ru.antonivanov87.prim.Edge(node1=496, node2=500, weight=-1519) as last edge" in {

    expectResult(Edge(node1=496, node2=500, weight= -1519))(edges.last)

  }

}
