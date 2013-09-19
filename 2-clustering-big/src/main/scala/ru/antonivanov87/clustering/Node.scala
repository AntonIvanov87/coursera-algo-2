package ru.antonivanov87.clustering

import scala.annotation.tailrec

case class Node(index: Int, bits: List[Boolean]) {

  def distanceTo(that: Node): Int = {

    distanceTo(that, Int.MaxValue)

  }

  def distanceTo(that: Node, maxDistance: Int) = {

    @tailrec
    def distanceAcc(bits1: List[Boolean], bits2: List[Boolean], distance: Int): Int = {

      (bits1, bits2) match {
        case (Nil, _) => distance + bits2.length
        case (_, Nil) => distance + bits1.length
        case (x::xs, y::ys) => {
          val newDistance: Int = if (x == y) distance else distance + 1
          if (newDistance > maxDistance)
            newDistance
          else
            distanceAcc(xs, ys, newDistance)
        }
      }

    }

    distanceAcc(this.bits, that.bits, 0)

  }

}
