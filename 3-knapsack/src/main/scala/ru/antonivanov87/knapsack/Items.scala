package ru.antonivanov87.knapsack

import scala.io.Source

object Items {

  def fromSource(source: Source): Pair[Int, List[Item]] = {

    val allLines: Iterator[String] = source.getLines()

    val knapsackSize: Int = allLines.next().split(" ")(0).toInt

    val itemsIterator: Iterator[Item] = for (itemLine <- allLines) yield {
      val lineParts: Array[String] = itemLine split " "
      Item(value=lineParts(0).toInt, weight =lineParts(1).toInt)
    }
    (knapsackSize, itemsIterator.toList)

  }

}
