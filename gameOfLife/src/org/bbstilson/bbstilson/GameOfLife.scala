package org.bbstilson.gol

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object GameOfLife extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title.value = "Game of Life"
    width = 500
    height = 500
    // scene = new Scene {
    //   fill = LightCoral
    //   content = new Rectangle {
    //     x = 25
    //     y = 40
    //     width = 100
    //     height = 100
    //     fill <== when(hover) choose Green otherwise Red
    //   }
    // }
  }
}
