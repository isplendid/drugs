name := """h2-database"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  //"org.slf4j" % "slf4j-nop" % "1.6.4",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "eu.imind.play" %% "play-cxf_play25" % "1.3.0",
  "mysql" % "mysql-connector-java" % "6.0.5",
  "commons-jxpath" % "commons-jxpath" % "1.3",
  "org.springframework" % "spring-context" % "4.3.3.RELEASE",
  "eu.imind.play" %% "play-cxf_play25" % "1.3.0",
  "org.apache.cxf" % "cxf-rt-bindings-soap" % "3.1.2",
  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.2",
  "com.h2database" % "h2" % "1.3.170" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test
)

