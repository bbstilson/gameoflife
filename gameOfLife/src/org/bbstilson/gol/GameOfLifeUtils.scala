package org.bbstilson.gol

object GameOfLifeUtils {

  def calcNeighborsWithScreenSizes(
    width: Int,
    height: Int,
    cellSize: Int
  )(x: Int, y: Int): List[Vector2] = {
    val up = Vector2(x, if (y - cellSize < 0) height - cellSize else y - cellSize)
    val down = Vector2(x, (y + cellSize) % height)
    val left = Vector2(if (x - cellSize < 0) width - cellSize else x - cellSize, y)
    val right = Vector2((x + cellSize) % width, y)
    val upLeft = up.copy(x = left.x)
    val upRight = up.copy(x = right.x)
    val downLeft = down.copy(x = left.x)
    val downRight = down.copy(x = right.x)
    List(up, down, left, right, upLeft, upRight, downLeft, downRight)
  }
}
