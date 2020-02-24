package org.bbstilson.gol

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure
import scala.util.Success

/**
  * The universe of the Game of Life is an infinite, two-dimensional orthogonal
  * grid of square cells, each of which is in one of two * possible states, alive
  * or dead, (or populated and unpopulated, respectively). Every cell interacts
  * with its eight neighbors, *which are the cells that are horizontally,
  * vertically, or diagonally adjacent.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val gol = new GameOfLife()
    gol.main(args)
  }
}
