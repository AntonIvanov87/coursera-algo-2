package ru.antonivanov87.clustering

import scala.io.Source
import scala.annotation.tailrec
import ru.antonivanov87.graph.Edge

object Nodes {

  def fromSource(source: Source): List[Node] = {

    val allLines: Iterator[String] = source.getLines()
    val nodesLines: Iterator[String] = allLines drop 1

    @tailrec
    def iterate(nodesLines: Iterator[String], lineNo: Int, nodes: List[Node]): List[Node] = {

      if (nodesLines.isEmpty) {
        nodes.reverse
      } else {
        val nodeLine: String = nodesLines.next()
        val lineParts: Array[String] = nodeLine split " "
        val bits: List[Boolean] = lineParts.map((part: String) => if (part == "1") true else false).toList
        iterate(nodesLines, lineNo + 1, Node(lineNo, bits) :: nodes)
      }

    }

    iterate(nodesLines, 1, Nil)

  }

  def toEdges(nodes: List[Node], maxDistance: Int): List[Edge] = {

    @tailrec
    def outerLoop(nodes: List[Node], listOfEdges: List[List[Edge]]): List[Edge] = {

      nodes match {
        case Nil => listOfEdges.flatten
        case x::xs => {
          println("processing node " + x.index)
          outerLoop(xs, innerLoop(x, xs) :: listOfEdges)
        }
      }

    }

    def innerLoop(node1: Node, nodes: List[Node]): List[Edge] =
      nodes.foldLeft[List[Edge]](Nil)((edges, node2) => {

        val distance: Int = node1.distanceTo(node2, maxDistance)
        if (distance <= maxDistance) Edge(node1.index, node2.index, distance)::edges else edges

      })

    outerLoop(nodes, Nil)

  }

}
