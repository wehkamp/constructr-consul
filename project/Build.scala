import bintray.BintrayPlugin
import com.typesafe.sbt.{ GitPlugin, SbtScalariform }
import de.heikoseeberger.sbtheader.HeaderPlugin
import de.heikoseeberger.sbtheader.license.Apache2_0
import sbt._
import sbt.plugins.JvmPlugin
import sbt.Keys._
import scalariform.formatter.preferences.{ AlignSingleLineCaseStatements, DoubleIndentClassDeclaration }

object Build extends AutoPlugin {

  override def requires = JvmPlugin && HeaderPlugin && GitPlugin && SbtScalariform && BintrayPlugin

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
      "-encoding", "UTF-8"
    ),
    unmanagedSourceDirectories.in(Compile) := Vector(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Vector(scalaSource.in(Test).value),

    // Scalariform settings
    SbtScalariform.autoImport.scalariformPreferences := SbtScalariform.autoImport.scalariformPreferences.value
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
      .setPreference(DoubleIndentClassDeclaration, true),

    // Git settings
    GitPlugin.autoImport.git.useGitDescribe := true,

    // Header settings
    HeaderPlugin.autoImport.headers := Map("scala" -> Apache2_0("2016", "TECNOLOGIA, SISTEMAS Y APLICACIONES S.L.")),

    // Bintray settings
    BintrayPlugin.autoImport.bintrayPackage := "constructr-consul"
  )
}
