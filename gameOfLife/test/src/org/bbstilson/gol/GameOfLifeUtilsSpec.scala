package org.bbstilson.gol

import scalafx.scene.paint.Color._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait UnitSpec extends AnyFlatSpec with Matchers

class GameOfLifeUtilsSpec extends UnitSpec {
  import GameOfLifeUtils._

  "calcNeighborsWithScreenSizes" should "calculate neighbors" in {
    val f = calcNeighborsWithScreenSizes(3, 3, 1)(_, _)
    f(0, 0) shouldBe List(Vector2(0, 1), Vector2(1, 0), Vector2(1, 1))
    f(1, 0) shouldBe List(
      Vector2(1, 1),
      Vector2(0, 0),
      Vector2(2, 0),
      Vector2(0, 1),
      Vector2(2, 1)
    )
    f(2, 0) shouldBe List(Vector2(2, 1), Vector2(1, 0), Vector2(1, 1))
    f(0, 1) shouldBe List(
      Vector2(0, 0),
      Vector2(0, 2),
      Vector2(1, 1),
      Vector2(1, 0),
      Vector2(1, 2)
    )
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
    f(2, 1) shouldBe List(
      Vector2(2, 0),
      Vector2(2, 2),
      Vector2(1, 1),
      Vector2(1, 0),
      Vector2(1, 2)
    )
    f(0, 2) shouldBe List(Vector2(0, 1), Vector2(1, 2), Vector2(1, 1))
    f(1, 2) shouldBe List(
      Vector2(1, 1),
      Vector2(0, 2),
      Vector2(2, 2),
      Vector2(0, 1),
      Vector2(2, 1)
    )
    f(2, 2) shouldBe List(Vector2(2, 1), Vector2(1, 2), Vector2(1, 1))
  }

  "pickColor" should "pick colors" in {
    pickColor(Some(1)) shouldBe RED
  }
}
