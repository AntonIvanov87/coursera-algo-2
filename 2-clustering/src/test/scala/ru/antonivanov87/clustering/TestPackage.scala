package ru.antonivanov87.clustering

import org.scalatest.FlatSpec
import ru.antonivanov87.graph.{Edge}

class TestPackage extends FlatSpec {

  val edge12 = Edge(1, 2, weight=1)
  val edge13 = Edge(1, 3, weight=3)
  val edge14 = Edge(1, 4, weight=4)
  val edge23 = Edge(2, 3, weight=4)
  val edge24 = Edge(2, 4, weight=3)
  val edge34 = Edge(3, 4, weight=2)
  val trapeziumGraph: Set[Edge] = Set(edge12, edge13, edge14, edge23, edge24, edge34)

  "getMaxSpacing" should "find maximum spacing" in {

    val getMaxSpacingOfTrapeziumGraph: Int => Int = getMaxSpacing(trapeziumGraph)
    expectResult(1)(getMaxSpacingOfTrapeziumGraph(4))
    expectResult(2)(getMaxSpacingOfTrapeziumGraph(3))
    expectResult(3)(getMaxSpacingOfTrapeziumGraph(2))

  }

}
