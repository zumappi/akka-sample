name := "akka-sample"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion = "2.5.3"
  val logbackVersion = "1.2.3"

  Seq(
    // akka
    "com.typesafe.akka" %% "akka-slf4j"   % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,

    // akka-actor
    "com.typesafe.akka" %% "akka-actor"   % akkaVersion,

    // logging
    "com.typesafe.scala-logging" %% "scala-logging"   % "3.1.0",
    "ch.qos.logback"             %  "logback-classic" % logbackVersion
  )
}