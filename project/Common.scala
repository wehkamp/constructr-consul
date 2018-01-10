import com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin.autoImport.{ scalafmtOnCompile, scalafmtVersion }
import com.typesafe.sbt.GitPlugin
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._
import de.heikoseeberger.sbtheader._
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

object Common extends AutoPlugin {

  override def requires = JvmPlugin && HeaderPlugin && GitPlugin

  override def trigger = allRequirements

  override def projectSettings =
    Seq(
      // Core settings
      organization := "com.tecsisa",
      organizationName := "Tecnología, Sistemas y Aplicaciones S.L.",
      organizationHomepage := Some(url("http://www.tecsisa.com/")),
      scmInfo := Some(
        ScmInfo(
          url("https://github.com/Tecsisa/constructr-consul"),
          "git@github.com:Tecsisa/constructr-consul.git")),
      developers += Developer(
        "contributors",
        "Contributors",
        "",
        url("https://github.com/Tecsisa/constructr-consul/graphs/contributors")),
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
      // Git settings
      GitPlugin.autoImport.git.useGitDescribe := true,
      // Header settings
      headerLicense := Some(
        HeaderLicense
          .ALv2("2016, 2017", "TECNOLOGIA, SISTEMAS Y APLICACIONES S.L. <http://www.tecsisa.com>")
      ),
      headerMappings := headerMappings.value ++ Map(
        FileType.scala -> CommentStyle.cStyleBlockComment
      ),
      // Scalafmt settings
      scalafmtOnCompile in ThisBuild := true,
      scalafmtVersion in ThisBuild := Version.Scalafmt
    )
}
