import sbt._

object Version {
  final val Scala            = "2.11.8"
  final val ConstructrAkka   = "0.11.1-4-g263b264"
  final val RaptureJsonSpray = "1.1.0"
}

object Library {
  val constructrAkka       = "de.heikoseeberger"  % "constructr-akka_2.11"   % Version.ConstructrAkka
  val raptureJsonSpray     = "com.propensive"     %% "rapture-json-spray"    % Version.RaptureJsonSpray
}