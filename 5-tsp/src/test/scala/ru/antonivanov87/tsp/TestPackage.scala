package ru.antonivanov87.tsp

import org.scalatest.FlatSpec

import ru.antonivanov87.points.Point

class TestPackage extends FlatSpec {

  "getMinLength" should "return min tsp length for 2 points" in {

    val points = Vector(
      Point(0, 0),
      Point(0, 1)
    )

    val actualMinLength = getMinLength(points)

    assertResult(2)(actualMinLength)

  }

  it should "return min tsp length for 3 points" in {

    val points = Vector(
      Point(0, 0),
      Point(1, 1),
      Point(0, 2)
    )

    val actualMinLength = getMinLength(points)

    assertResult(2*math.sqrt(2) + 2)(actualMinLength)

  }

  it should "return min tsp length for 4 points" in {

    val points = Vector(
      Point(0, 0),
      Point(0, 1),
      Point(1, 1),
      Point(1, 0)
    )

    val actualMinLength = getMinLength(points)

    assertResult(4)(actualMinLength)

  }

  "initMap" should "have size = 1" in {
    assertResult(1)(initMap.size)
  }

  it should "have size = 1 of its' value" in {
    assertResult(1)(initMap(Set(0)).size)
  }

  it should "have 0.0 as value of inner map" in {
    assertResult(0.0)(initMap(Set(0))(0))
  }

  "getSetsOfNextSize" should "return all sets of size 2 for initMap and 2 points" in {

    val actualSets = getSetsOfNextSize(Set(Set(0)), 2)
    assertResult(1)(actualSets.size)
    assume(actualSets contains Set(0, 1))

  }

  it should "return all sets of size 2 for initMap and 3 points" in {

    val actualSets = getSetsOfNextSize(Set(Set(0)), 3)
    assertResult(2)(actualSets.size)
    assume(actualSets contains Set(0, 1))
    assume(actualSets contains Set(0, 2))

  }

  it should "return all sets of size 3 for 3 points" in {

    val actualSets = getSetsOfNextSize(
      Set(Set(0, 1), Set(0, 2)),
      numOfPoints = 3
    )

    assertResult(1)(actualSets.size)
    assume(actualSets contains Set(0, 1, 2))

  }

}
