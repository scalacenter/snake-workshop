
lazy val snake = project
  .in(file("."))
  .settings(
    scalaVersion := "3.0.2",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += ("org.scala-js" %%% "scalajs-dom" % "1.2.0").cross(CrossVersion.for3Use2_13),
  ).enablePlugins(ScalaJSPlugin)
