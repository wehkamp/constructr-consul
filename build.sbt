lazy val construtrRoot = project
  .copy(id = "constructr-root")
  .in(file("."))
  .enablePlugins(GitVersioning)
  .aggregate(constructrCoordinationConsul, constructrCoordinationTesting)

lazy val constructrCoordinationConsul = project
  .copy(id = "constructr-coordination-consul")
  .in(file("constructr-coordination-consul"))
  .enablePlugins(AutomateHeaderPlugin)

lazy val constructrCoordinationTesting = project
  .copy(id = "constructr-coordination-testing")
  .in(file("constructr-coordination-testing"))
  .configs(MultiJvm)
  .dependsOn(constructrCoordinationConsul % "test->compile")

name := "constructr-root"

unmanagedSourceDirectories in Compile := Vector.empty
unmanagedSourceDirectories in Test    := Vector.empty

publishArtifact := false