package org.bbstilson.gol

import javafx.scene.{paint => jfxsp}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp._
import scalafx.beans.property.ObjectProperty
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

import scala.concurrent.duration._
import scala.collection.mutable.{Map => MutableMap}
import scalafx.animation.Timeline
import scalafx.animation.Timeline.Indefinite

/**
  * The universe of the Game of Life is an infinite, two-dimensional orthogonal
  * grid of square cells, each of which is in one of two * possible states, alive
  * or dead, (or populated and unpopulated, respectively). Every cell interacts
  * with its eight neighbors, *which are the cells that are horizontally,
  * vertically, or diagonally adjacent.
  */
class GameOfLife extends JFXApp {
  import GameOfLifeUtils._

  private val HEADER_HEIGHT = 22

  private val windowWidth = 600
  private val windowHeight = 600
  private val cellSize = 20
  val calcNeighbors = calcNeighborsWithScreenSizes(windowWidth, windowHeight, cellSize)(_, _)

  val pixels = for {
    y <- 0 until windowHeight by cellSize
    x <- 0 until windowWidth by cellSize
  } yield Vector2(x, y)

  val world: MutableMap[Vector2, Cell] = {
    val m = MutableMap.empty[Vector2, Cell]
    pixels.foreach {
      case pos @ Vector2(px, py) =>
        m(pos) = Cell(calcNeighbors(px, py))
    }
    m
  }

  val colorMap: MutableMap[Vector2, ObjectProperty[jfxsp.Color]] = {
    val m = MutableMap.empty[Vector2, ObjectProperty[jfxsp.Color]]
    world.foreach {
      case (pos, cell) =>
        m(pos) = ObjectProperty(ageToColor(cell.age))
    }
    m
  }

  stage = new PrimaryStage {
    width = windowWidth.toDouble
    height = windowHeight.toDouble + HEADER_HEIGHT
    scene = new Scene {
      fill = WHITE
      content = pixels.map {
        case pos @ Vector2(px, py) =>
          new Rectangle {
            x = px
            y = py
            width = cellSize
            height = cellSize
            fill <== colorMap(pos)
          }
      }
    }
  }

  // new Timeline {
  //   cycleCount = Indefinite
  //   autoReverse = true
  //   keyFrames =
  // }

  // def run(): Unit = {
  //   while (true) {
  //     Thread.sleep(1.seconds.toMillis)
  //     world.map {
  //       case (pos, cell) =>
  //         cell.rect.fill.value = pickColor(Some(Math.floor(Math.random * 7).toInt))
  //     }
  //   }
  // }
}
