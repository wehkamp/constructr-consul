import com.typesafe.sbt.GitPlugin
import de.heikoseeberger.sbtheader.HeaderPlugin
import de.heikoseeberger.sbtheader.license.Apache2_0
import sbt._
import sbt.plugins.JvmPlugin
import sbt.Keys._

object Common extends AutoPlugin {

  override def requires = JvmPlugin && HeaderPlugin && GitPlugin

  override def trigger = allRequirements

  override def projectSettings = Seq(
    // Core settings
    organization := "com.tecsisa",
    organizationName := "Tecnología, Sistemas y Aplicaciones S.L.",
    organizationHomepage := Some(url("http://www.tecsisa.com/")),
    scmInfo := Some(ScmInfo(url("https://github.com/Tecsisa/constructr-consul"), "git@github.com:Tecsisa/constructr-consul.git")),
    developers += Developer("contributors", "Contributors", "", url("https://github.com/Tecsisa/constructr-consul/graphs/contributors")),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/Tecsisa/constructr-consul")),
    pomIncludeRepository := (_ => false),

    scalaVersion := crossScalaVersions.value.head,
    crossScalaVersions := Version.ScalaVersions,
    crossVersion := CrossVersion.binary,
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Ywarn-unused-import", // only 2.11
      "-Xfuture" // prevents of future breaking changes
    ),
    javacOptions ++= Seq(
      "-Xlint:unchecked"
    ),

    ivyScala := ivyScala.value.map(_.copy(overrideScalaVersion = sbtPlugin.value)), // TODO Remove once this workaround no longer needed (https://github.com/sbt/sbt/issues/2786)!

    // Git settings
    GitPlugin.autoImport.git.useGitDescribe := true,

    // Header settings
    HeaderPlugin.autoImport.headers := Map("scala" -> Apache2_0("2016, 2017", "TECNOLOGIA, SISTEMAS Y APLICACIONES S.L."))
  )
}
