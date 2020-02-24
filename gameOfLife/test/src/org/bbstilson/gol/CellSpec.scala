package org.bbstilson.gol

import scalafx.scene.paint.Color

class CellSpec extends UnitSpec {
  import Cell._

  "stateToColor" should "pick colors" in {
    stateToColor(Alive(1)) shouldBe Color(0.5, 0.8, 0.2, 0.3)
    stateToColor(Alive(1)) shouldBe Color(0.5, 0.8, 0.2, 0.3)
    stateToColor(Alive(2)) shouldBe Color(0.5, 0.8, 0.2, 0.4)
    stateToColor(Alive(3)) shouldBe Color(0.5, 0.8, 0.2, 0.5)
    stateToColor(Alive(4)) shouldBe Color(0.5, 0.8, 0.2, 0.6)
    stateToColor(Alive(5)) shouldBe Color(0.5, 0.8, 0.2, 0.7)
    stateToColor(Alive(6)) shouldBe Color(0.5, 0.8, 0.2, 0.9)
    stateToColor(Alive(7)) shouldBe Color(0.5, 0.8, 0.2, 1.0)
    stateToColor(Alive(10)) shouldBe Color(0.5, 0.8, 0.2, 1.0)
  }
}
