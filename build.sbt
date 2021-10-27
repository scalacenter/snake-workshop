
lazy val snake = project
  .in(file("."))
  .settings(
    scalaVersion := "3.0.2",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.0.0",
    libraryDependencies += "org.scalameta" %%% "munit" % "0.7.29"
  ).enablePlugins(ScalaJSPlugin)
