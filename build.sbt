import com.typesafe.sbt.SbtMultiJvm

lazy val `constructr-consul` = project
  .in(file("."))
  .enablePlugins(GitVersioning, NoPublish)
  .disablePlugins(BintrayPlugin)
  .aggregate(
    `constructr-coordination-consul`,
    `constructr-coordination-demo`,
    `constructr-coordination-testing`)

lazy val `constructr-coordination-consul` = project
  .in(file("constructr-coordination-consul"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "constructr-coordination-consul",
    libraryDependencies ++= Seq(
      Library.akkaHttp,
      Library.constructrAkka,
      Library.circeParser,
      Library.akkaTestkit % Test,
      Library.scalaTest   % Test
    )
  )

lazy val `constructr-coordination-testing` = project
  .in(file("constructr-coordination-testing"))
  .enablePlugins(AutomateHeaderPlugin, SbtMultiJvm, NoPublish)
  .disablePlugins(BintrayPlugin)
  .configs(MultiJvm)
  .settings(
    name := "constructr-coordination-testing",
    unmanagedSourceDirectories.in(MultiJvm) := Vector(scalaSource.in(MultiJvm).value),
    test.in(Test) := test.in(MultiJvm).dependsOn(test.in(Test)).value,
    libraryDependencies ++= Seq(
      Library.akkaMultiNodeTestkit % "test",
      Library.scalaTest            % "test"
    )
  )
  .dependsOn(`constructr-coordination-consul` % "test->compile")

lazy val `constructr-coordination-demo` = project
  .in(file("constructr-coordination-demo"))
  .enablePlugins(AutomateHeaderPlugin, AshScriptPlugin, NoPublish)
  .disablePlugins(BintrayPlugin)
  .dependsOn(`constructr-coordination-consul`)
  .settings(
    name := "constructr-coordination-demo",
    dockerSettings ++ Seq(
      libraryDependencies ++= Seq(
        Library.akkaLog4j,
        Library.log4jCore
      )
    ))

lazy val dockerSettings: Seq[Setting[_]] = Seq(
  maintainer in Docker := "Tecsisa",
  dockerBaseImage := "frolvlad/alpine-oraclejdk8",
  daemonUser in Docker := "root",
  version in Docker := "latest"
)
