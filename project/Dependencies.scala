import sbt._

object Version {
  final val Scala            = "2.11.8"
  final val ConstructrAkka   = "0.13.2"
  final val raptureJsonCirce = "2.0.0-M7"
  final val Akka             = "2.4.4"
  final val AkkaLog4j        = "1.1.3"
  final val ScalaTest        = "2.2.6"
  final val Log4j            = "2.5"
}

object Library {
  val constructrAkka       = "de.heikoseeberger"        %% "constructr-akka"              % Version.ConstructrAkka
  val akkaLog4j            = "de.heikoseeberger"        %% "akka-log4j"                   % Version.AkkaLog4j
  val log4jCore            = "org.apache.logging.log4j" %  "log4j-core"                   % Version.Log4j
  val raptureJsonCirce     = "com.propensive"           %% "rapture-json-circe"           % Version.raptureJsonCirce
  val akkaMultiNodeTestkit = "com.typesafe.akka"        %% "akka-multi-node-testkit"      % Version.Akka
  val scalaTest            = "org.scalatest"            %% "scalatest"                    % Version.ScalaTest
}
