package ru.antonivanov87.graph

case class Edge(node1: Int, node2: Int, weight: Int) {

  require(node1 < node2)

}
