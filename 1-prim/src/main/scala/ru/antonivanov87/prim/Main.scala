package ru.antonivanov87.prim

import scala.io.Source

import ru.antonivanov87.graph.{Edges, Edge}

object Main {

  def main(args: Array[String]) {

    val source: Source = Source fromURL (getClass getResource "/edges.txt")
    val graph: Set[Edge] = Edges.fromSource(source).toSet
    val graphMST: Set[Edge] = MST(graph)
    val primSumOfEdges: Int = sumOfEdges(graphMST)
    println("Prim sum of edges: " + primSumOfEdges)

  }

}
