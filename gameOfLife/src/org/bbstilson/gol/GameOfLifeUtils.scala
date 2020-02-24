package org.bbstilson.gol

import javafx.scene.{paint => jfxsp}
import scalafx.beans.property.ObjectProperty
import scalafx.scene.paint.Color
import Color._

object GameOfLifeUtils {
  case class Vector2(x: Int, y: Int)
  case class Cell(color: ObjectProperty[jfxsp.Color], neighbors: List[Vector2])

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

  def pickColor(optAge: Option[Int]): Color = {
    optAge
      .map {
        case 1 => RED
        case 2 => ORANGE
        case 3 => YELLOW
        case 4 => GREEN
        case 5 => BLUE
        case 6 => VIOLET
      }
      .getOrElse(WhiteSmoke)
  }
}
