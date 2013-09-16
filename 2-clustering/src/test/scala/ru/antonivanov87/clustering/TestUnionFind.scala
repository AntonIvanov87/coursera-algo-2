package ru.antonivanov87.clustering

import org.scalatest.FlatSpec

class TestUnionFind extends FlatSpec {

  private val initUnionFind: UnionFind = UnionFind(Set(1, 2, 3))
  private val branchUnionFind: UnionFind = new UnionFind(
    elem2Parent=Map(1 -> 2, 2 -> 3, 3 -> 4, 4 -> 4),
    elem2Size=Map(1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4)
  )

  "UnionFind(elements)" should "initialize each element point to himself" in {

    expectResult(1)(initUnionFind getRoot 1)
    expectResult(2)(initUnionFind getRoot 2)
    expectResult(3)(initUnionFind getRoot 3)

  }

  "UnionFind.union" should "attach smaller subtree to bigger one" in {

    val ufNew: UnionFind = initUnionFind union (1, 2)

    assert((ufNew getRoot 1) === (ufNew getRoot 2))
    expectResult(3)(ufNew getRoot 3)

    val ufNewNew: UnionFind = ufNew union (1, 3)
    assert((ufNewNew getRoot 3) != 3)
    assert((ufNewNew getRoot 1) === (ufNewNew getRoot 3))

  }

  "UnionFind.compress" should "repoint each element along path to its' root" in {

    val compressed: UnionFind = branchUnionFind.compress(1)

    expectResult(Map(1 -> 4, 2 -> 4, 3 -> 4, 4 -> 4))(compressed.elem2Parent)

  }

  "UnionFind.getElemsToCompress" should "get map of (elem -> root) for compression" in {

    val elemsToCompress: Map[Int, Int] = branchUnionFind getElemsToCompress 1

    expectResult(Map(1 -> 4, 2 -> 4, 3 -> 4, 4 -> 4))(elemsToCompress)

  }

}
