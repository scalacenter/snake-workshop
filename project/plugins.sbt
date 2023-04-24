addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.7.0")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.3")
// fix Scala.js Com failed: Error: connect ECONNREFUSED ::1:xxxxx - https://github.com/scala-js/scala-js-js-envs/issues/12#issuecomment-958925883
libraryDependencies += "org.scala-js" %% "scalajs-env-nodejs" % "1.2.1"
