package org.bbstilson.gol

import scalafx.Includes._
import scalafx.animation.Timeline
import Timeline.Indefinite
import scalafx.application.JFXApp
import scalafx.application.JFXApp._
import scalafx.beans.property.ObjectProperty
import scalafx.scene.paint.Color.WHITE
import scalafx.scene.Scene
import scalafx.scene.shape.Rectangle

import scala.collection.mutable.{Map => MutableMap}
import scalafx.scene.paint.Paint

/**
  * The universe of the Game of Life is an infinite, two-dimensional orthogonal
  * grid of square cells, each of which is in one of two * possible states, alive
  * or dead, (or populated and unpopulated, respectively). Every cell interacts
  * with its eight neighbors, *which are the cells that are horizontally,
  * vertically, or diagonally adjacent.
  */
object GameOfLife extends JFXApp {
  import GameOfLifeUtils._

  private val HEADER_HEIGHT = 22

  private val windowWidth = 60
  private val windowHeight = 60
  private val cellSize = 20

  private val world: MutableMap[Vector2, Cell] = {
    val m = MutableMap.empty[Vector2, Cell]
    val calcNeighbors = calcNeighborsWithScreenSizes(windowWidth, windowHeight, cellSize)(_, _)
    val pixels = for {
      y <- 0 until windowHeight by cellSize
      x <- 0 until windowWidth by cellSize
    } {
      val optAge = if (Math.random > 0.95) Some(1) else None
      m(Vector2(x, y)) = Cell(ObjectProperty(pickColor(optAge)), calcNeighbors(x, y))
    }
    m
  }

  stage = new PrimaryStage {
    width = windowWidth.toDouble
    height = windowHeight.toDouble + HEADER_HEIGHT
    scene = new Scene { _scene =>
      fill = WHITE
      content = world.map {
        case (pos, Cell(color, neighbors)) =>
          new Rectangle {
            x = pos.x
            y = pos.y
            width = cellSize
            height = cellSize
            fill <== color
          }
      }
    }
  }
}
