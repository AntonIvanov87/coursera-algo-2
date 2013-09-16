package ru.antonivanov87.clustering

import scala.io.Source
import ru.antonivanov87.graph.{Edge, Edges}

object Main {

  def main(args: Array[String]) {

    val source: Source = Source fromURL (getClass getResource "/clustering1.txt")
    val distances: Set[Edge] = (Edges fromSource source).toSet
    val maxSpacing: Int = getMaxSpacing(distances)(4)
    println("Max spacing of 4 clusters: " + maxSpacing)


  }

}
