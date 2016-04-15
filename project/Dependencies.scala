import sbt._

object Version {
  final val Scala            = "2.11.8"
  final val ConstructrAkka   = "0.13.0"
  final val RaptureJsonSpray = "1.1.0"
  final val Akka             = "2.4.4"
  final val ScalaTest        = "2.2.6"
}

object Library {
  val constructrAkka       = "de.heikoseeberger"  %% "constructr-akka"              % Version.ConstructrAkka
  val raptureJsonSpray     = "com.propensive"     %% "rapture-json-spray"           % Version.RaptureJsonSpray
  val akkaMultiNodeTestkit = "com.typesafe.akka"  %% "akka-multi-node-testkit"      % Version.Akka
  val scalaTest            = "org.scalatest"      %% "scalatest"                    % Version.ScalaTest
}
