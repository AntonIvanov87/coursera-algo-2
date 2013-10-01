package ru.antonivanov87.apsp

import scala.io.Source
import ru.antonivanov87.graph.{Edges, Edge}

object Main {

  def main(args: Array[String]) = {

    val source1: Source = Source.fromURL(getClass.getResource("/g1.txt"))
    val source2: Source = Source.fromURL(getClass.getResource("/g2.txt"))
    val source3: Source = Source.fromURL(getClass.getResource("/g3.txt"))

    val graph1: Set[Edge] = Edges.fromSource(source1).toSet
    val graph2: Set[Edge] = Edges.fromSource(source2).toSet
    val graph3: Set[Edge] = Edges.fromSource(source3).toSet

    val minPathLength1: Int = getMinPathLength(graph1)
    println("finished g1")
    val minPathLength2: Int = getMinPathLength(graph2)
    println("finished g2")
    val minPathLength3: Int = getMinPathLength(graph3)
    println("finished g3")

    println("g1: " + minPathLength1)
    println("g2: " + minPathLength2)
    println("g3: " + minPathLength3)

  }

}
