name := "hello-scala-sbt"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.9" % Test

//libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"

// https://mvnrepository.com/artifact/com.amazonaws/amazon-sqs-java-messaging-lib
libraryDependencies += "com.amazonaws" % "amazon-sqs-java-messaging-lib" % "1.0.8"

// https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-core
libraryDependencies += "com.amazonaws" % "aws-java-sdk-core" % "1.11.942"
