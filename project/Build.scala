import bintray.BintrayPlugin
import com.typesafe.sbt.GitPlugin
import de.heikoseeberger.sbtheader.HeaderPlugin
import de.heikoseeberger.sbtheader.license.Apache2_0
import org.scalafmt.sbt.ScalaFmtPlugin
import sbt._
import sbt.plugins.JvmPlugin
import org.scalafmt.sbt.ScalaFmtPlugin.autoImport._
import sbt.Keys._

object Build extends AutoPlugin {

  override def requires = JvmPlugin && HeaderPlugin && GitPlugin && BintrayPlugin && ScalaFmtPlugin

  override def trigger = allRequirements

  override def projectSettings = Vector(
    // Core settings
    organization := "com.tecsisa",
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/Tecsisa/constructr-consul")),
    pomIncludeRepository := (_ => false),
    pomExtra := <scm>
      <url>https://github.com/Tecsisa/constructr-consul</url>
      <connection>scm:git:git@github.com:Tecsisa/constructr-consul</connection>
    </scm>
      <developers>
        <developer>
          <id>juanjovazquez</id>
          <name>Juanjo Vazquez</name>
          <url>http://www.tecsisa.com</url>
        </developer>
        <developer>
          <id>gerson24</id>
          <name>Gerson Pozo</name>
          <url>http://www.tecsisa.com</url>
        </developer>
      </developers>,
    scalaVersion := Version.Scala,
    crossScalaVersions := Vector(scalaVersion.value),
    scalacOptions ++= Vector(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ywarn-unused-import"
    ),
    unmanagedSourceDirectories.in(Compile) := Vector(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Vector(scalaSource.in(Test).value),

    // Git settings
    GitPlugin.autoImport.git.useGitDescribe := true,

    // Header settings
    HeaderPlugin.autoImport.headers := Map("scala" -> Apache2_0("2016", "TECNOLOGIA, SISTEMAS Y APLICACIONES S.L.")),

    // Bintray settings
    BintrayPlugin.autoImport.bintrayPackage := "constructr-consul",

    // scalafmt settings
    formatSbtFiles := false,
    scalafmtConfig := Some(baseDirectory.in(ThisBuild).value / ".scalafmt.conf")
  )
}
