name := "constructr-coordination-demo"

libraryDependencies ++= Vector(
  Library.akkaLog4j,
  Library.log4jCore
)

initialCommands := """|import com.tecsisa.constructr.coordination.demo._""".stripMargin

publishArtifact := false