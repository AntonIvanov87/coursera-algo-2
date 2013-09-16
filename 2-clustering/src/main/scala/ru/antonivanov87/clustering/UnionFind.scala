package ru.antonivanov87.clustering

import scala.annotation.tailrec

class UnionFind(
  private[clustering] val elem2Parent: Map[Int, Int],
  private[clustering] val elem2Size: Map[Int, Int]) {

  @tailrec
  final def getRoot(elem: Int): Int = {
    val parent = elem2Parent(elem)
    if (parent == elem) parent else getRoot(parent)
  }

  def union(elem1: Int, elem2: Int): UnionFind = {

    val compressed: UnionFind = this compress elem1 compress elem2
    val root1: Int = getRoot(elem1)
    val root2: Int = getRoot(elem2)

    val root1Size: Int = compressed.elem2Size(root1)
    val root2Size: Int = compressed.elem2Size(root2)

    if (root1Size == root2Size) {
      new UnionFind(
        compressed.elem2Parent + (root1 -> root2),
        compressed.elem2Size + (root2 -> (root2Size+1))
      )
    } else if (root1Size > root2Size) {
      new UnionFind(
        compressed.elem2Parent + (root2 -> root1),
        compressed.elem2Size
      )
    } else {
      new UnionFind(
        compressed.elem2Parent + (root1 -> root2),
        compressed.elem2Size
      )
    }

  }

  private[clustering] def compress(elem: Int): UnionFind = {

    val elemsToCompress: Map[Int, Int] = this getElemsToCompress elem
    if (elemsToCompress.isEmpty) {
      this
    } else {
      new UnionFind(this.elem2Parent ++ elemsToCompress, this.elem2Size)
    }

  }

  private[clustering] def getElemsToCompress(elem: Int): Map[Int, Int] = {

    val parent: Int = this elem2Parent elem
    if (parent == elem) {
      Map(elem -> parent)
    } else {
      val elemsToCompressParent: Map[Int, Int] = this getElemsToCompress parent
      elemsToCompressParent + (elem -> elemsToCompressParent(parent))
    }

  }

}

object UnionFind {

  def apply(elements: Set[Int]): UnionFind = {

    val elem2Parent: Map[Int, Int] = elements.foldLeft[Map[Int, Int]](Map())((map, elem) => map + (elem -> elem))
    val elem2Size: Map[Int, Int] = elem2Parent.transform((elem, parent) => 1)
    new UnionFind(elem2Parent, elem2Size)

  }

}
