package ru.antonivanov87.points

import org.scalatest.FlatSpec
import scala.io.Source

class TestPackage extends FlatSpec {

  val points: Vector[Point] = pointsFromSource(
    Source fromURL (getClass getResource "/tsp.txt")
  )

  "fromFile" should "read 25 points" in {

    assertResult(25)(points.size)

  }

  it should "read Point(20833.3333, 17100.0000) as first point" in {

    assertResult(Point(20833.3333, 17100.0000))(points.head)

  }

  it should "read Point(27233.3333, 10450.0000) as last point" in {

    assertResult(Point(27233.3333, 10450.0000))(points.last)

  }

}
