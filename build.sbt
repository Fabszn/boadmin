import Dependencies.*
import sbt.io.IO

import scala.language.postfixOps
import scala.sys.process.Process
import scala.util.{Failure, Try}

ThisBuild / scalaVersion := "3.3.0"
ThisBuild / useCoursier := false

lazy val front = (project in file("front"))

httpResourceDir := (This / Compile / resourceDirectory).value
lazy val frontProd = taskKey[Unit]("package Prod mode")
lazy val frontDev  = taskKey[Unit]("package Dev mode")

lazy val frontCopyFile   = taskKey[Unit]("prepare and copy file to engine directory")
lazy val frontCleanFiles = taskKey[Unit]("clean directories")
lazy val yarnInstall     = taskKey[Unit]("install front project")
lazy val httpResourceDir = settingKey[File]("resource directory")

def yarnInstall(file: File) =
  Process("yarn install", file) !

front / yarnInstall := {
  if (yarnInstall(front.base) != 0) throw new Exception("Something went wrong when running yarn install.")
}

def buildFront(file: File, mode: String) =
  Process(
    s"yarn vite build --mode ${mode}",
    file
  ) !

frontDev := {
  if (buildFront(front.base, "development") != 0) throw new Exception("Something went wrong when running yarn.")
}

frontProd := {
  if (buildFront(front.base, "production") != 0) throw new Exception("Something went wrong when running yarn.")
}

frontCopyFile := {
  Try {
    //clean
    IO.delete((httpResourceDir.value / "index.html"))
    IO.delete((httpResourceDir.value / "assets"))

    IO.copyDirectory(
      (front / baseDirectory).value / "dist/assets",
      (httpResourceDir.value / "public" / "assets")
    )
    IO.copyFile(
      (front / baseDirectory).value / "dist" / "index.html",
      (httpResourceDir.value / "index.html")
    )
  } match {
    case Failure(exception) => throw exception
    case _ => ()
  }

}

frontCleanFiles := {
  Try {
    IO.delete((httpResourceDir.value / "dist/"))
  } match {
    case Failure(exception) => throw exception
    case _ => ()
  }

}

addCommandAlias(
  "runDev",
  ";frontDev;frontCopyFile;run"
)
addCommandAlias(
  "build",
  ";frontProd;frontCopyFile;clean;compile"
)

addCommandAlias(
  "build2Prod",
  ";clean;front/yarnInstall;frontProd;frontCopyFile;dist"
)

lazy val boadmin = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
        zioPrelude,
        logstashEncoder,
        logback,
        log4jBridge,
        zioLogging,
        zioLoggingSlf4j,
        zioHttp,
        quillJdbcZio,
        driverJdbc,
        flyway,
        zioJson
      ) ++
      zio ++
      zioTest ++
      fs2Libs ++
      zioConfigLibs
  )
