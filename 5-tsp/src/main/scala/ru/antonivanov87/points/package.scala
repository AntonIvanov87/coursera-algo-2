package ru.antonivanov87

import scala.io.Source

package object points {

  def fromSource(source: Source): Vector[Point] = {

    val allLines: Iterator[String] = source.getLines()
    val pointsLines: Iterator[String] = allLines drop 1
    val pointsIterator: Iterator[Point] =
      for (edgeLine <- pointsLines) yield {
        val lineParts: Array[String] = edgeLine split " "
        Point(lineParts(0).toDouble, lineParts(1).toDouble)
      }
    pointsIterator.toVector

  }

}
