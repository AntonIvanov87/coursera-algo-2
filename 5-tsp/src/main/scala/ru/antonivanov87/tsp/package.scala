package ru.antonivanov87

import ru.antonivanov87.points.Point
import scala.annotation.tailrec
import scala.compat.Platform
import scala.collection.immutable.{HashMap}

package object tsp {

  def getMinLength(points: Vector[Point]): Double = {

    val almostResult = incSetsSizes(points, initMap(points.size))

    val finalSet = almostResult.keys.head
    val innerMap = almostResult.values.head
    (for (interPointIndex <- 1 until finalSet.size) yield {
      val fromInterToDest = points(interPointIndex) distanceTo points(0)
      innerMap(interPointIndex) + fromInterToDest
    }).min

  }

  private[tsp] def initMap(numOfPoints: Int): Map[Vector[Boolean], Map[Int, Double]] =
    HashMap(
      (for(i <- 0 until numOfPoints) yield if (i==0) true else false).toVector -> HashMap(0 -> 0.0)
    )

  @tailrec
  private[tsp] def incSetsSizes(points: Vector[Point],
                                prevMap: Map[Vector[Boolean], Map[Int, Double]]
                                 ): Map[Vector[Boolean], Map[Int, Double]] = {

    def getInnerMap(set: Vector[Boolean]): Map[Int, Double] = {

      (for (destPointIndex <- 1 until set.size if set(destPointIndex)) yield {
        val setWithoutDest = set updated (destPointIndex, false)
        val minDistance =
          (for (interPointIndex <- 0 until setWithoutDest.size if setWithoutDest(interPointIndex)) yield {

            val from0ToInter = prevMap(setWithoutDest)(interPointIndex)
            if (from0ToInter == Double.MaxValue) {
              Double.MaxValue
            } else {
              val fromInterToDest = points(interPointIndex) distanceTo points(destPointIndex)
              from0ToInter + fromInterToDest
            }

          }).min
        (destPointIndex, minDistance)

      }).toMap.withDefaultValue(Double.MaxValue)

    }

    val prevSetSize = prevMap.keys.head.foldLeft[Int](0)((sum, value) => if (value) sum+1 else sum)
    if (prevSetSize == points.size) {
      prevMap

    } else {

      println("cur set size: " + (prevSetSize + 1))
      val beforeGetSets = Platform.currentTime
      val sets = getSetsOfNextSize(prevMap.keySet, points.size)
      println("getSetsOfNextSize in " + (Platform.currentTime - beforeGetSets) + " ms")

      val beforeNewMap = Platform.currentTime
      val newMap =
        (for (set <- sets) yield
          (set, getInnerMap(set))
        ).toMap
      println("newMap in " + (Platform.currentTime - beforeNewMap) + " ms")

      incSetsSizes(points, newMap)

    }

  }

  private[tsp] def getSetsOfNextSize(prevSets: Set[Vector[Boolean]], numOfPoints: Int): Set[Vector[Boolean]] = {

    (for (pointIndex <- 1 until numOfPoints) yield
      for (set <- prevSets if !set(pointIndex)) yield
        set updated (pointIndex, true)
      ).flatten.toSet

  }

}
