package org.bbstilson.gol

import scalafx.beans.property.ObjectProperty
import scalafx.scene.paint.Color
import Color._

object GameOfLifeUtils {
  case class Vector2(x: Int, y: Int)
  case class Cell(age: Option[Int], neighbors: List[Vector2])

  object Cell {

    def apply(neighbors: List[Vector2]): Cell = Cell(randomAge, neighbors)

    def randomAge: Option[Int] = {
      if (Math.random > 0.95) Some(1) else None
    }
  }

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

  def ageToColor(optAge: Option[Int]): Color = {
    optAge
      .map {
        case 1 => RED
        case 2 => ORANGE
        case 3 => YELLOW
        case 4 => GREEN
        case 5 => BLUE
        case 6 => VIOLET
      }
      .getOrElse(WHITESMOKE)
  }
}
