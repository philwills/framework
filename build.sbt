import Dependencies._


scalaVersion in ThisBuild := "2.10.0-RC1"

scalaBinaryVersion in ThisBuild   <<= scalaVersion

organization in ThisBuild          := "net.liftweb"

version in ThisBuild               := "3.0-SNAPSHOT"

homepage in ThisBuild              := Some(url("http://www.liftweb.net"))

licenses in ThisBuild              += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

startYear in ThisBuild             := Some(2006)

organizationName in ThisBuild      := "WorldWide Conferencing, LLC"

crossScalaVersions in ThisBuild    := Seq("2.10.0-RC1")

parallelExecution in ThisBuild := false

// fork := true

// fork in ThisBuild := true

// fork in test := true

libraryDependencies in ThisBuild ++= Seq(specs2, scalacheck, scala_compiler("2.10.0-RC1"))

// Settings for Sonatype compliance
pomIncludeRepository in ThisBuild  := { _ => false }

publishTo in ThisBuild            <<= isSnapshot(if (_) Some(Opts.resolver.sonatypeSnapshots) else Some(Opts.resolver.sonatypeStaging))

scmInfo in ThisBuild               := Some(ScmInfo(url("https://github.com/lift/framework"), "scm:git:https://github.com/lift/framework.git"))

pomExtra in ThisBuild              ~= (_ ++ {Developers.toXml})

credentials in ThisBuild <+= state map { s => Credentials(BuildPaths.getGlobalSettingsDirectory(s, BuildPaths.getGlobalBase(s)) / ".credentials") }

initialize <<= (name, version, scalaVersion) apply printLogo
