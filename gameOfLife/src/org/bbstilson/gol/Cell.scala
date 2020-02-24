package org.bbstilson.gol

import scalafx.scene.paint.Color
import Color._

case class Cell(state: State, neighbors: List[Vector2]) {

  def age: Cell = {
    val nextState = state match {
      case Alive(age) => Alive(age + 1)
      case Dead       => Dead
    }
    this.copy(state = nextState)
  }

  def isDead: Boolean = state match {
    case Alive(_) => false
    case Dead     => true
  }
}

object Cell {

  def apply(neighbors: List[Vector2]): Cell = Cell(randomState, neighbors)

  private def randomState: State = {
    if (Math.random > 0.7) Alive(1) else Dead
  }

  def stateToColor(state: State): Color = {
    state match {
      case Alive(age) =>
        age match {
          case 1 => Color(0.5, 0.8, 0.2, 0.3)
          case 2 => Color(0.5, 0.8, 0.2, 0.4)
          case 3 => Color(0.5, 0.8, 0.2, 0.5)
          case 4 => Color(0.5, 0.8, 0.2, 0.6)
          case 5 => Color(0.5, 0.8, 0.2, 0.7)
          case 6 => Color(0.5, 0.8, 0.2, 0.9)
          case _ => Color(0.5, 0.8, 0.2, 1.0)
        }
      case Dead => WHITESMOKE
    }
  }
}
