name := "discount"

version := "1.0"

scalaVersion := "2.12.1"

//libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
val scalatest =  "org.scalatest" %% "scalatest" % "3.0.4" % "test"

lazy val commonSettings = Seq(
  organization := "com.mastercoding",
  version := "1.0",
  scalaVersion := "2.12.1"
)

val testDependencies = Seq (
  scalatest
)

lazy val step1 = (project in file("step1"))
  .settings(
    commonSettings ++ Seq (libraryDependencies ++= testDependencies)
    // other settings
  )

lazy val step2 = (project in file("step2"))
  .settings(
    commonSettings ++ Seq (libraryDependencies ++= testDependencies)
    // other settings
  )

lazy val step3 = (project in file("step3"))
  .settings(
    commonSettings ++ Seq (libraryDependencies ++= testDependencies)
    // other settings
  )

lazy val step4 = (project in file("step4"))
  .settings(
    commonSettings ++ Seq (libraryDependencies ++= testDependencies)
    // other settings
  )

lazy val step5 = (project in file("step5"))
  .settings(
    commonSettings ++ Seq (libraryDependencies ++= testDependencies)
    // other settings
  )

lazy val step6 = (project in file("step6"))
  .settings(
    commonSettings ++ Seq (libraryDependencies ++= testDependencies)
    // other settings
  )


lazy val root = (project in file("."))
  .aggregate(step1, step2, step3, step4, step5, step6)