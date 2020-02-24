package org.bbstilson.gol

case class Vector2(x: Int, y: Int)

/**
  * Represents individual cell.
  *
  * @param age - None if dead.
  * @param position
  */
case class Cell(age: Option[Int], position: Vector2) {

  def tick(numNeighbors: Int): Cell = {
    numNeighbors match {
      // Any dead cell with three live neighbors becomes a live cell.
      // Any live cell with two or three neighbors survives.
      // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
      case n if n == 3 && age.isEmpty => this.copy(age = Some(1))
      case n if n == 2 || n == 3      => this.copy(age = age.map(_ + 1))
      case n if n > 3                 => this.copy(age = None)
    }
  }
}
