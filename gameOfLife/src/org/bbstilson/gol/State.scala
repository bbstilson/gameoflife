package org.bbstilson.gol

sealed trait State
case class Alive(age: Int) extends State
case object Dead extends State
