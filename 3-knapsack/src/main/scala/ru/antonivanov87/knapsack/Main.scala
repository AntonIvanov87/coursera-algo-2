package ru.antonivanov87.knapsack

import scala.io.Source

object Main {

  def main(args: Array[String]) = {

    val source: Source = Source fromURL (getClass getResource "/knapsack1.txt")
    val maxWeightAndItems: Pair[Int, List[Item]] = Items fromSource source
    val computedMaxValue = maxValue(maxWeightAndItems._1)(maxWeightAndItems._2)
    println("max value: " + computedMaxValue)

  }

}
