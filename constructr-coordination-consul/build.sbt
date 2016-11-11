name := "constructr-coordination-consul"

libraryDependencies ++= Vector(
  Library.akkaHttp,
  Library.constructrAkka,
  Library.raptureJsonCirce
)

initialCommands := """|import com.tecsisa.constructr.coordination.consul._""".stripMargin