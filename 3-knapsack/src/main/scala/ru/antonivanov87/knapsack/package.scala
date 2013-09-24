package ru.antonivanov87

import scala.annotation.tailrec

package object knapsack {

  def maxValue(maxWeight: Int)(items: List[Item]): Int = {

    @tailrec
    def maxItemValues(remainingItems: List[Item], prevMaxValues: IndexedSeq[Int]): Int = {

      if (remainingItems.isEmpty) {
        prevMaxValues.last

      } else {

        val curItem = remainingItems.head
        val curMaxValues: IndexedSeq[Int] = for (weight <- 0 to maxWeight) yield {

          val valueWithout: Int = prevMaxValues(weight)
          if (curItem.weight > weight) {
            valueWithout

          } else {
            val valueWith: Int = curItem.value + prevMaxValues(weight - curItem.weight)
            if (valueWith > valueWithout) valueWith else valueWithout

          }

        }

        maxItemValues(remainingItems.tail, curMaxValues)

      }

    }

    maxItemValues(items, for(i <- 0 to maxWeight) yield 0)

  }

}
