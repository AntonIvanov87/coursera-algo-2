package ru.antonivanov87.points

case class Point(x: Double, y: Double) {

  def distanceTo(that: Point): Double = {
    val diffX = x - that.x
    val diffY = y - that.y
    math.sqrt(diffX * diffX + diffY * diffY)
  }

}
