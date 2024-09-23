import Dependencies.version.*
import sbt.*

object Dependencies {

  object version {
    lazy val zioVersion     = "2.1.5"
    lazy val zioHttpVersion = "3.0.0-RC9"


    lazy val zioConfigVersion  = "4.0.0-RC16"
    lazy val zioPreludeVersion = "1.0.0-RC21"


    lazy val logbackVersion    = "1.2.12"
    lazy val logstashVersion   = "7.2"
    lazy val slf4jVersion      = "1.7.36"
    lazy val log4j2Version     = "2.20.0"
    lazy val zioLoggingVersion = "2.1.15"

    lazy val fs2Version               = "3.9.3"
    lazy val ziocatsInteropVersion    = "23.1.0.0"
    lazy val catsVersion              = "2.10.0"
    lazy val catsEffectVersion        = "3.5.2"
    lazy val sttpClientVersion        = "3.9.1"
    lazy val sttpLoggerSlf4jVersion   = "3.5.2"
    lazy val refinedVersion           = "0.11.0"
    lazy val refinedZioConfigVersion  = "4.0.0-RC16"
    lazy val circeVersion             = "0.14.6"
    lazy val circeGenericExtraVersion = "0.14.3"
    lazy val scalaCacheVersion        = "1.0.0-M6"
    lazy val catsLogVersion           = "2.6.0"

    lazy val zioMetricsVersion        = "2.1.0"
    lazy val quillJdbcZioVersion      = "4.8.0"
    lazy val jdbcVersion              = "42.7.1"
    lazy val flywayVersion            = "9.6.0"
    lazy val zioJsonVersion           = "0.6.2"
  }

  lazy val zioHttp = "dev.zio" %% "zio-http" % zioHttpVersion
  lazy val zio = Seq(
    "dev.zio" %% "zio"          % zioVersion
  )
  lazy val zioConfig            = "dev.zio"             %% "zio-config"             % zioConfigVersion
  lazy val zioConfigMagnolia    = "dev.zio"             %% "zio-config-magnolia"    % zioConfigVersion
  lazy val zioConfigTypeSafe    = "dev.zio"             %% "zio-config-typesafe"    % zioConfigVersion
  lazy val zioConfigRefined     = "dev.zio"             %% "zio-config-refined"     % zioConfigVersion
  lazy val zioJson              = "dev.zio"             %% "zio-json"               % zioJsonVersion
  lazy val zioConfigLibs        = Seq(zioConfig, zioConfigMagnolia, zioConfigTypeSafe, zioConfigRefined)
  lazy val driverJdbc   = "org.postgresql" % "postgresql"     % jdbcVersion
  lazy val quillJdbcZio = "io.getquill"   %% "quill-jdbc-zio" % quillJdbcZioVersion
  lazy val flyway       = "org.flywaydb"   % "flyway-core"    % flywayVersion

  lazy val fs2Core = "co.fs2" %% "fs2-core" % fs2Version

  lazy val fs2Io = "co.fs2" %% "fs2-io" % fs2Version

  lazy val fs2Libs    = Seq(fs2Core, fs2Io)

  lazy val zioPrelude = "dev.zio"       %% "zio-prelude" % zioPreludeVersion

  lazy val zioLoggingSlf4j = "dev.zio"                 %% "zio-logging-slf4j"        % zioLoggingVersion
  lazy val zioLogging      = "dev.zio"                 %% "zio-logging"              % zioLoggingVersion
  lazy val logback         = "ch.qos.logback"           % "logback-classic"          % logbackVersion
  lazy val log4jBridge     = "org.apache.logging.log4j" % "log4j-to-slf4j"           % log4j2Version
  lazy val logstashEncoder = "net.logstash.logback"     % "logstash-logback-encoder" % logstashVersion
  lazy val zioTest =
    Seq(
      "dev.zio" %% "zio-test"          % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt"      % zioVersion % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion  % Test
    )

}
