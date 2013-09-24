package ru.antonivanov87.knapsack

import org.scalatest.FlatSpec
import scala.io.Source

class TestItems extends FlatSpec {

  val source: Source = Source fromURL (getClass getResource "/knapsack1.txt")
  val knapsackSizeAndItems: Pair[Int, List[Item]] = Items.fromSource(source)
  val knapsackSize: Int = knapsackSizeAndItems._1
  val items: Vector[Item] = knapsackSizeAndItems._2.toVector

  "fromSource" should "return knapsackSize = 10000" in {

    expectResult(10000)(knapsackSize)

  }

  it should "return 100 items" in {

    expectResult(100)(items.length)

  }

  it should "return Item(value=16808, weight=250) as 1st item" in {

    expectResult(Item(value=16808, weight=250))(items.head)

  }

  it should "return Item(nvalue=54680, weight=153) as last item" in {

    expectResult(Item(value=54680, weight=153))(items.last)

  }

}
