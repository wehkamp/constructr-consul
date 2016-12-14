import sbt._

object Version {
  final val Akka             = "2.4.14"
  final val AkkaHttp         = "10.0.0"
  final val AkkaLog4j        = "1.2.0"
  final val ConstructrAkka   = "0.15.0"
  final val Log4j            = "2.7"
  final val Circe            = "0.6.1"
  final val Scala            = "2.12.1"
  final val ScalaTest        = "3.0.1"
}

object Library {
  val akkaHttp             = "com.typesafe.akka"        %% "akka-http"               % Version.AkkaHttp
  val akkaLog4j            = "de.heikoseeberger"        %% "akka-log4j"              % Version.AkkaLog4j
  val akkaMultiNodeTestkit = "com.typesafe.akka"        %% "akka-multi-node-testkit" % Version.Akka
  val constructrAkka       = "de.heikoseeberger"        %% "constructr"              % Version.ConstructrAkka
  val log4jCore            = "org.apache.logging.log4j" %  "log4j-core"              % Version.Log4j
  val circeParser          = "io.circe"                 %% "circe-parser"            % Version.Circe
  val scalaTest            = "org.scalatest"            %% "scalatest"               % Version.ScalaTest
}
