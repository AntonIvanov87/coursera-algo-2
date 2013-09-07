package ru.antonivanov87.prim

import org.scalatest.FlatSpec

class TestEdge extends FlatSpec {

  "edge with node1 > node2" should "throw IllegalArgumentException" in {

    intercept[IllegalArgumentException](Edge(node1=2, node2=1, weight=10))

  }

}

