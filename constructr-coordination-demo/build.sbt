name := "constructr-coordination-demo"

version := "1.0.0"

libraryDependencies ++= Vector(
  Library.akkaLog4j,
  Library.log4jCore
)

lazy val dockerSettings: Seq[Setting[_]] = Seq(
  maintainer in Docker := "Tecsisa",
  dockerBaseImage := "frolvlad/alpine-oraclejdk8",
  daemonUser in Docker := "root",
  version in Docker := "latest"
)

initialCommands := """|import com.tecsisa.constructr.coordination.demo._""".stripMargin