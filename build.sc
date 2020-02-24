import mill._, scalalib._

object gameOfLife extends ScalaModule {
  def scalaVersion = "2.13.1"

  // Determine OS version of JavaFX binaries
  lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _                            => throw new Exception("Unknown platform!")
  }

  // Add dependency on JavaFX libraries, OS dependent
  val javaFXModules = List("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => ivy"org.openjfx:javafx-$m:12.0.2;classifier=$osName")

  def ivyDeps = {
    Agg(
      ivy"org.scalafx::scalafx:12.0.2-R18"
    ) ++ javaFXModules
  }

  object test extends Tests {

    def ivyDeps = Agg(
      ivy"org.scalactic::scalactic:3.1.1",
      ivy"org.scalatest::scalatest:3.1.1"
    )
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}
