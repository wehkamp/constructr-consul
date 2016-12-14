import com.typesafe.sbt.GitPlugin
import de.heikoseeberger.sbtheader.HeaderPlugin
import de.heikoseeberger.sbtheader.license.Apache2_0
import sbt._
import sbt.plugins.JvmPlugin
import org.scalafmt.sbt.ScalaFmtPlugin.autoImport._
import sbt.Keys._

object Common extends AutoPlugin {

  override def requires = JvmPlugin && HeaderPlugin && GitPlugin

  override def trigger = allRequirements

  override def projectSettings = Vector(
    // Core settings
    organization := "com.tecsisa",
    organizationName := "Tecnología, Sistemas y Aplicaciones S.L.",
    organizationHomepage := Some(url("http://www.tecsisa.com/")),
    scmInfo := Some(ScmInfo(url("https://github.com/Tecsisa/constructr-consul"), "git@github.com:Tecsisa/constructr-consul.git")),
    developers += Developer("contributors", "Contributors", "", url("https://github.com/Tecsisa/constructr-consul/graphs/contributors")),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/Tecsisa/constructr-consul")),
    pomIncludeRepository := (_ => false),

    scalaVersion := Version.ScalaVersions.head,
    crossScalaVersions := Version.ScalaVersions,
    crossVersion := CrossVersion.binary,

    scalacOptions ++= Vector(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ywarn-unused-import"
    ),

    ivyScala := ivyScala.value.map(_.copy(overrideScalaVersion = sbtPlugin.value)), // TODO Remove once this workaround no longer needed (https://github.com/sbt/sbt/issues/2786)!

    // Git settings
    GitPlugin.autoImport.git.useGitDescribe := true,

    // Header settings
    HeaderPlugin.autoImport.headers := Map("scala" -> Apache2_0("2016", "TECNOLOGIA, SISTEMAS Y APLICACIONES S.L.")),

    // scalafmt settings
    formatSbtFiles := false,
    scalafmtConfig := Some(baseDirectory.in(ThisBuild).value / ".scalafmt.conf")
  )
}
