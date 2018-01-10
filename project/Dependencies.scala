import sbt._

object Version {
  final val Akka           = "2.5.8"
  final val AkkaHttp       = "10.0.11"
  final val AkkaLog4j      = "1.5.0"
  final val ConstructrAkka = "0.18.0"
  final val Log4j          = "2.9.1"
  final val Circe          = "0.9.0"
  final val ScalaVersions  = Seq("2.12.4", "2.11.12")
  final val ScalaTest      = "3.0.4"
  final val Scalafmt       = "1.4.0"
}

object Library {
  val akkaHttp             = "com.typesafe.akka"        %% "akka-http"               % Version.AkkaHttp
  val akkaLog4j            = "de.heikoseeberger"        %% "akka-log4j"              % Version.AkkaLog4j
  val akkaMultiNodeTestkit = "com.typesafe.akka"        %% "akka-multi-node-testkit" % Version.Akka
  val akkaTestkit          = "com.typesafe.akka"        %% "akka-testkit"            % Version.Akka
  val constructrAkka       = "de.heikoseeberger"        %% "constructr"              % Version.ConstructrAkka
  val log4jCore            = "org.apache.logging.log4j" % "log4j-core"               % Version.Log4j
  val circeParser          = "io.circe"                 %% "circe-parser"            % Version.Circe
  val scalaTest            = "org.scalatest"            %% "scalatest"               % Version.ScalaTest
}
