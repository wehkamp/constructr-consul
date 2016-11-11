name := "constructr-coordination-testing"

libraryDependencies ++= Vector(
  Library.akkaMultiNodeTestkit % "test",
  Library.scalaTest            % "test"
)

initialCommands := """|import com.tecsisa.constructr.akka.consul._""".stripMargin

unmanagedSourceDirectories.in(MultiJvm) := Vector(scalaSource.in(MultiJvm).value)

test.in(Test) := { test.in(MultiJvm).value; test.in(Test).value }

inConfig(MultiJvm)(reformatOnCompileSettings)
inConfig(MultiJvm) {
  compileInputs.in(compile) := {
    scalafmt.value
    compileInputs.in(compile).value
  }
}

publishArtifact := false