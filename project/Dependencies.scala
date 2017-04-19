import sbt._

object Version {
  final val Akka           = "2.4.17"
  final val AkkaHttp       = "10.0.5"
  final val AkkaLog4j      = "1.3.0"
  final val ConstructrAkka = "0.17.0"
  final val Log4j          = "2.8.2"
  final val Circe          = "0.7.1"
  final val ScalaVersions  = Seq("2.12.1", "2.11.8")
  final val ScalaTest      = "3.0.1"
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
