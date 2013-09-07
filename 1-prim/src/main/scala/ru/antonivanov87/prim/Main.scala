package ru.antonivanov87.prim

import scala.io.Source

object Main {

  def main(args: Array[String]) {

    val source: Source = Source fromURL (getClass getResource "/edges.txt")
    val graph: Set[Edge] = EdgesFromFile.read(source).toSet
    val graphMST: Set[Edge] = MST(graph)
    val primSumOfEdges: Int = sumOfEdges(graphMST)
    println("Prim sum of edges: " + primSumOfEdges)

  }

}
