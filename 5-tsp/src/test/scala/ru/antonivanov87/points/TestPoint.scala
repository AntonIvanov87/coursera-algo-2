package ru.antonivanov87.points

import org.scalatest.FlatSpec

class TestPoint extends FlatSpec {

  "distanceTo" should "return 0 for same point" in {

    val actualDistance = Point(1, 2) distanceTo Point(1, 2)
    assertResult(0)(actualDistance)

  }

  it should "return 1" in {

    val actualDistance = Point(0, 0) distanceTo Point(0, 1)
    assertResult(1)(actualDistance)

  }

  it should "return sqrt(2)" in {

    val actualDistance = Point(0, 0) distanceTo Point(1, 1)
    assertResult(math.sqrt(2))(actualDistance)

  }

}
