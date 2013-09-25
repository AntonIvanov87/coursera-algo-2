package ru.antonivanov87.knapsack_big

import org.scalatest.FlatSpec
import ru.antonivanov87.knapsack.{Items, Item}
import scala.io.Source

class TestPackage extends FlatSpec {

  "maxValue" should "return sum of values of all items if their weight <= maxWeight" in {

    val item1: Item = Item(value=10, weight=1)
    val item2: Item = Item(value=20, weight=2)

    val actualMaxValue = maxValue(3)(List(item1, item2))

    expectResult(30)(actualMaxValue)

  }

  it should "return 0 if all items are heavier than maxWeight" in {

    val item1: Item = Item(value=10, weight=10)
    val item2: Item = Item(value=20, weight=20)

    val actualMaxValue = maxValue(1)(List(item1, item2))

    expectResult(0)(actualMaxValue)

  }

  it should "return max value" in {

    val source: Source = Source fromURL (getClass getResource "/knapsack1.txt")
    val maxWeightAndItems: Pair[Int, List[Item]] = Items fromSource source
    val computedMaxValue = maxValue(maxWeightAndItems._1)(maxWeightAndItems._2)

    expectResult(2493893)(computedMaxValue)

  }

}
