name := "constructr-coordination-consul"

resolvers += Resolver.bintrayRepo("hseeberger", "maven")

libraryDependencies ++= Vector(
  Library.constructrAkka,
  Library.raptureJsonSpray
)

initialCommands := """|import com.tecsisa.constructr.coordination.consul._""".stripMargin