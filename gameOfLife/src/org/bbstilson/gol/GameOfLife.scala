package org.bbstilson.gol

import javafx.scene.{paint => jfxsp}
import scalafx.Includes._
import scalafx.animation._
import scalafx.application.JFXApp
import scalafx.application.JFXApp._
import scalafx.beans.property.ObjectProperty
import scalafx.event._
import scalafx.scene._
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.util.{Duration => FxDuration}

import scala.collection.mutable.{Map => MutableMap}

object GameOfLife extends JFXApp {
  import GameOfLifeUtils._

  private val HEADER_HEIGHT = 22

  private val windowWidth = 600
  private val windowHeight = 600
  private val cellSize = 6
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

  val color: MutableMap[Vector2, ObjectProperty[jfxsp.Color]] = {
    val m = MutableMap.empty[Vector2, ObjectProperty[jfxsp.Color]]
    world.foreach {
      case (pos, cell) =>
        m(pos) = ObjectProperty(Cell.stateToColor(cell.state))
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
            fill <== color(pos)
          }
      }
    }
  }

  val timeline = new Timeline {
    cycleCount = Timeline.Indefinite
    keyFrames = KeyFrame(FxDuration(100), onFinished = step)
  }.play()

  def step(e: ActionEvent): Unit = {
    pixels.foreach {
      case pos @ Vector2(px, py) =>
        val currCell = world(pos)
        val numAliveNeighbors = currCell.neighbors
          .map(world.apply)
          .map(_.state)
          .collect { case Alive(_) => 1 }
          .sum

        numAliveNeighbors match {
          // Any dead cell with three live neighbors becomes a live cell.
          // Any live cell with two or three neighbors survives.
          // All other cells die/stay dead in the next generation.
          case n if n == 3 && currCell.isDead => resurrect(pos, currCell)
          case n if n == 2 || n == 3          => ageCell(pos, currCell)
          case _                              => killCell(pos, currCell)
        }
    }
  }

  private def resurrect(v: Vector2, c: Cell): Unit = {
    val newCell = c.copy(state = Alive(1))
    world(v) = newCell
    color(v).value = Cell.stateToColor(newCell.state)
  }

  private def ageCell(v: Vector2, c: Cell): Unit = {
    val newCell = c.age
    world(v) = newCell
    color(v).value = Cell.stateToColor(newCell.state)
  }

  private def killCell(v: Vector2, c: Cell): Unit = {
    val newCell = c.copy(state = Dead)
    world(v) = newCell
    color(v).value = Cell.stateToColor(newCell.state)
  }
}
