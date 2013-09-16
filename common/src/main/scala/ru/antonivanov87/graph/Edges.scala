package ru.antonivanov87.graph

import scala.io.Source

object Edges {

  def sumOfWeights(graph: Set[Edge]): Int =
    graph.foldLeft[Int](0)(_ + _.weight)

  def fromSource(source: Source): List[Edge] = {

    val allLines: Iterator[String] = source.getLines()
    val edgesLines: Iterator[String] = allLines drop 1
    val edgesIterator: Iterator[Edge] = for (edgeLine <- edgesLines) yield {
      val lineParts: Array[String] = edgeLine split " "
      Edge(node1=lineParts(0).toInt,
        node2=lineParts(1).toInt,
        weight=lineParts(2).toInt)
    }
    edgesIterator.toList

  }

}
