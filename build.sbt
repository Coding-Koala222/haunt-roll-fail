ThisBuild / scalaVersion := "2.13.14"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.codingkoala"

lazy val root = (project in file("."))
  .settings(
    name := "haunt-roll-fail",

    // If your app has a main method, set it here:
    // Compile / mainClass := Some("com.codingkoala.Main"),

    // sbt-assembly: stable output filename
    assembly / assemblyJarName := "haunt-roll-fail.jar",

    // Avoid duplicate META-INF / config merge issues
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) =>
        xs.map(_.toLowerCase) match {
          case "manifest.mf" :: Nil => MergeStrategy.discard
          case "index.list" :: Nil  => MergeStrategy.discard
          case "dependencies" :: Nil => MergeStrategy.discard
          case ps if ps.lastOption.exists(_.endsWith(".sf"))   => MergeStrategy.discard
          case ps if ps.lastOption.exists(_.endsWith(".dsa"))  => MergeStrategy.discard
          case ps if ps.lastOption.exists(_.endsWith(".rsa"))  => MergeStrategy.discard
          case _ => MergeStrategy.first
        }
      case "reference.conf" => MergeStrategy.concat
      case "application.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )
