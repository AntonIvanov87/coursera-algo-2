package ru.antonivanov87.clustering

import org.scalatest.FlatSpec

class TestNode extends FlatSpec {

  "distanceTo" should "return 0 for equal nodes" in {

    val node1: Node = Node(1, true :: false :: Nil)
    val node2: Node = Node(2, true :: false :: Nil)

    val distance: Int = node1 distanceTo node2

    expectResult(0)(distance)

  }

  it should "return number of different bits" in {

    val node1: Node = Node(1, true :: false :: Nil)
    val node2: Node = Node(2, true :: true :: Nil)

    val distance: Int = node1 distanceTo node2

    expectResult(1)(distance)

    val revDistance: Int = node2 distanceTo node1

    expectResult(1)(revDistance)

  }

  "distanceTo" should "return maxDistance+1 when actual distance is bigger than maxDistance" in {

    val node1: Node = Node(1, true :: false :: Nil)
    val node2: Node = Node(2, false :: true :: Nil)

    val distance: Int = node1.distanceTo(node2, 0)
    expectResult(1)(distance)

  }

}
