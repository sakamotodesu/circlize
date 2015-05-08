name := "circlize"

version := "0.1"

lazy val `circlize` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws, "postgresql" % "postgresql" % "9.3-1102-jdbc41")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )