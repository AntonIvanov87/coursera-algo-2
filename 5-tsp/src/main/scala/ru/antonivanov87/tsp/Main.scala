package ru.antonivanov87.tsp

import ru.antonivanov87.points.fromSource
import scala.io.Source

object Main {

  def main(args: Array[String]) = {

    val points = fromSource(Source.fromURL(getClass.getResource("/tsp.txt")))
    val minLength = getMinLength(points)
    println("min TSP length: " + minLength)

  }

}
