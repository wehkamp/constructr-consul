import sbt._

object Version {
  final val Akka             = "2.4.11"
  final val AkkaLog4j        = "1.1.5"
  final val ConstructrAkka   = "0.14.0"
  final val Log4j            = "2.6.2"
  final val RaptureJsonCirce = "2.0.0-M7"
  final val Scala            = "2.11.8"
  final val ScalaTest        = "3.0.0"
}

object Library {
  val akkaHttp             = "com.typesafe.akka"        %% "akka-http-experimental"  % Version.Akka
  val akkaLog4j            = "de.heikoseeberger"        %% "akka-log4j"              % Version.AkkaLog4j
  val akkaMultiNodeTestkit = "com.typesafe.akka"        %% "akka-multi-node-testkit" % Version.Akka
  val constructrAkka       = "de.heikoseeberger"        %% "constructr"              % Version.ConstructrAkka
  val log4jCore            = "org.apache.logging.log4j" %  "log4j-core"              % Version.Log4j
  val raptureJsonCirce     = "com.propensive"           %% "rapture-json-circe"      % Version.RaptureJsonCirce
  val scalaTest            = "org.scalatest"            %% "scalatest"               % Version.ScalaTest
}
