package org.bbstilson.gol

object GameOfLifeUtils {

  def calcNeighborsWithScreenSizes(
    width: Int,
    height: Int,
    cellSize: Int
  )(x: Int, y: Int): List[Vector2] = {
    List(
      // up
      if (y - cellSize < 0) None else Some(Vector2(x, y - cellSize)),
      // down
      if (y + cellSize > (height - cellSize)) None else Some(Vector2(x, y + cellSize)),
      // left
      if (x - cellSize < 0) None else Some(Vector2(x - cellSize, y)),
      // right
      if (x + cellSize > (width - cellSize)) None else Some(Vector2(x + cellSize, y)),
      // up left
      if (x - cellSize < 0 || y - cellSize < 0) None
      else Some(Vector2(x - cellSize, y - cellSize)),
      // up right
      if (x + cellSize > (width - cellSize) || y - cellSize < 0) None
      else Some(Vector2(x + cellSize, y - cellSize)),
      // down left
      if (x - cellSize < 0 || y + cellSize > (height - cellSize)) None
      else Some(Vector2(x - cellSize, y + cellSize)),
      // down right
      if (x + cellSize > (width - cellSize) || y + cellSize > (height - cellSize)) None
      else Some(Vector2(x + cellSize, y + cellSize))
    ).flatten
  }
}
