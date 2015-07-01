name := "circlize"

version := "0.1"

lazy val `circlize` = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "jp.t2v" %% "play2-auth" % "0.13.2",
  "jp.t2v" %% "play2-auth-test" % "0.13.2" % "test")

libraryDependencies ++= Seq(jdbc, anorm, cache, ws, "postgresql" % "postgresql" % "9.1-901-1.jdbc4")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")