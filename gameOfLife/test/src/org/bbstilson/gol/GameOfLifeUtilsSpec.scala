package org.bbstilson.gol

import scalafx.scene.paint.Color._

class GameOfLifeUtilsSpec extends UnitSpec {
  import GameOfLifeUtils._

  val f = calcNeighborsWithScreenSizes(3, 3, 1)(_, _)

  "calcNeighborsWithScreenSizes" should "calculate neighbors - top left corner" in {
    f(0, 0) shouldBe List(
      Vector2(0, 2),
      Vector2(0, 1),
      Vector2(2, 0),
      Vector2(1, 0),
      Vector2(2, 2),
      Vector2(1, 2),
      Vector2(2, 1),
      Vector2(1, 1)
    )
  }
  it should "calculate neighbors - top middle" in {
    f(1, 0) shouldBe List(
      Vector2(1, 2),
      Vector2(1, 1),
      Vector2(0, 0),
      Vector2(2, 0),
      Vector2(0, 2),
      Vector2(2, 2),
      Vector2(0, 1),
      Vector2(2, 1)
    )
  }

  it should "calculate neighbors - top right corner" in {
    f(2, 0) shouldBe List(
      Vector2(2, 2),
      Vector2(2, 1),
      Vector2(1, 0),
      Vector2(0, 0),
      Vector2(1, 2),
      Vector2(0, 2),
      Vector2(1, 1),
      Vector2(0, 1)
    )

  }
  it should "calculate neighbors - left middle" in {
    f(0, 1) shouldBe List(
      Vector2(0, 0),
      Vector2(0, 2),
      Vector2(2, 1),
      Vector2(1, 1),
      Vector2(2, 0),
      Vector2(1, 0),
      Vector2(2, 2),
      Vector2(1, 2)
    )
  }
  it should "calculate neighbors - middle" in {
    f(1, 1) shouldBe List(
      Vector2(1, 0),
      Vector2(1, 2),
      Vector2(0, 1),
      Vector2(2, 1),
      Vector2(0, 0),
      Vector2(2, 0),
      Vector2(0, 2),
      Vector2(2, 2)
    )

  }
  it should "calculate neighbors - right middle" in {
    f(2, 1) shouldBe List(
      Vector2(2, 0),
      Vector2(2, 2),
      Vector2(1, 1),
      Vector2(0, 1),
      Vector2(1, 0),
      Vector2(0, 0),
      Vector2(1, 2),
      Vector2(0, 2)
    )

  }
  it should "calculate neighbors - bottom left corner" in {
    f(0, 2) shouldBe List(
      Vector2(0, 1),
      Vector2(0, 0),
      Vector2(2, 2),
      Vector2(1, 2),
      Vector2(2, 1),
      Vector2(1, 1),
      Vector2(2, 0),
      Vector2(1, 0)
    )

  }
  it should "calculate neighbors - bottom middle" in {
    f(1, 2) shouldBe List(
      Vector2(1, 1),
      Vector2(1, 0),
      Vector2(0, 2),
      Vector2(2, 2),
      Vector2(0, 1),
      Vector2(2, 1),
      Vector2(0, 0),
      Vector2(2, 0)
    )
  }
  it should "calculate neighbors - bottom right corner" in {
    f(2, 2) shouldBe List(
      Vector2(2, 1),
      Vector2(2, 0),
      Vector2(1, 2),
      Vector2(0, 2),
      Vector2(1, 1),
      Vector2(0, 1),
      Vector2(1, 0),
      Vector2(0, 0)
    )
  }
}
