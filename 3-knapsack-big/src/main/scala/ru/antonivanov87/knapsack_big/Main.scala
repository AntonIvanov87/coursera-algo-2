package ru.antonivanov87.knapsack_big

import scala.io.Source
import ru.antonivanov87.knapsack.{Items, Item}

object Main {

  def main(args: Array[String]) = {

    val source: Source = Source fromURL (getClass getResource "/knapsack_big.txt")
    val maxWeightAndItems: Pair[Int, List[Item]] = Items fromSource source
    val computedMaxValue = maxValue(maxWeightAndItems._1)(maxWeightAndItems._2)
    println("max value: " + computedMaxValue)

  }

}
