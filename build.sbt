lazy val construtrRoot = project
  .copy(id = "constructr-root")
  .in(file("."))
  .enablePlugins(GitVersioning)
  .aggregate(constructrCoordinationConsul)

lazy val constructrCoordinationConsul = project
  .copy(id = "constructr-coordination-consul")
  .in(file("constructr-coordination-consul"))
  .enablePlugins(AutomateHeaderPlugin)

name := "constructr-root"

unmanagedSourceDirectories in Compile := Vector.empty
unmanagedSourceDirectories in Test    := Vector.empty

publishArtifact := false