name := "constructr-coordination-consul"

libraryDependencies ++= Vector(
  Library.constructrAkka,
  Library.raptureJsonCirce
)

initialCommands := """|import com.tecsisa.constructr.coordination.consul._""".stripMargin