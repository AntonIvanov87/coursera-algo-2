package ru.antonivanov87

import ru.antonivanov87.points.Point
import scala.annotation.tailrec
import scala.compat.Platform
import scala.collection.immutable.{HashSet, HashMap}

package object tsp {

  def getMinLength(points: Vector[Point]): Double = {

    val almostResult = incSetsSizes(points, initMap)

    val finalSet = almostResult.keys.head
    val innerMap = almostResult.values.head
    (for (interPointIndex <- finalSet if interPointIndex != 0) yield {
      val fromInterToDest = points(interPointIndex) distanceTo points(0)
      innerMap(interPointIndex) + fromInterToDest
    }).min

  }

  private[tsp] val initMap: Map[Set[Int], Map[Int, Double]] =
    HashMap(
      HashSet(0) -> HashMap(0 -> 0.0)
    )

  @tailrec
  private[tsp] def incSetsSizes(points: Vector[Point],
                                prevMap: Map[Set[Int], Map[Int, Double]]
                                 ): Map[Set[Int], Map[Int, Double]] = {

    def getInnerMap(set: Set[Int]): Map[Int, Double] = {

      (for (destPointIndex <- set if destPointIndex != 0) yield {
        val setWithoutDest = set - destPointIndex
        val minDistance =
          (for (interPointIndex <- setWithoutDest) yield {

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

    val prevSetSize = prevMap.keys.head.size
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

  private[tsp] def getSetsOfNextSize(prevSets: Set[Set[Int]], numOfPoints: Int): Set[Set[Int]] = {

    (for (pointIndex <- 1 until numOfPoints) yield
      for (set <- prevSets if !set.contains(pointIndex)) yield
        set + pointIndex
      ).flatten.toSet

  }

}
