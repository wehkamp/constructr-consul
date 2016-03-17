lazy val constructrConsul = project
  .copy(id = "constructr-consul")
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin, GitVersioning)

name := "constructr-consul"

libraryDependencies ++= Vector(
  Library.scalaCheck % "test"
)

initialCommands := """|import com.tecsisa.constructr.consul._
                      |""".stripMargin
