name := "constructr-coordination-consul"

libraryDependencies ++= Vector(
  Library.constructrAkka,
  Library.raptureJsonSpray
)

initialCommands := """|import com.tecsisa.constructr.coordination.consul._""".stripMargin