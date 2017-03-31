lazy val constructrRoot = project
  .copy(id = "constructr-consul-root")
  .in(file("."))
  .enablePlugins(GitVersioning, NoPublish)
  .disablePlugins(BintrayPlugin)
  .aggregate(constructrCoordinationConsul, constructrCoordinationDemo, constructrCoordinationTesting)

lazy val constructrCoordinationConsul = project
  .copy(id = "constructr-coordination-consul")
  .in(file("constructr-coordination-consul"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    libraryDependencies ++= Seq(
      Library.akkaHttp,
      Library.constructrAkka,
      Library.circeParser
    )
  )

lazy val constructrCoordinationTesting = project
  .copy(id = "constructr-coordination-testing")
  .in(file("constructr-coordination-testing"))
  .enablePlugins(NoPublish)
  .disablePlugins(BintrayPlugin)
  .configs(MultiJvm)
  .settings(
    unmanagedSourceDirectories.in(MultiJvm) := Vector(scalaSource.in(MultiJvm).value),
    test.in(Test) := test.in(MultiJvm).value,
    libraryDependencies ++= Seq(
      Library.akkaMultiNodeTestkit % "test",
      Library.scalaTest            % "test"
    )
  )
  .dependsOn(constructrCoordinationConsul % "test->compile")

lazy val constructrCoordinationDemo = project
  .copy(id = "constructr-coordination-demo")
  .in(file("constructr-coordination-demo"))
  .enablePlugins(AutomateHeaderPlugin, AshScriptPlugin, NoPublish)
  .disablePlugins(BintrayPlugin)
  .dependsOn(constructrCoordinationConsul)
  .settings(dockerSettings ++ Seq(
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