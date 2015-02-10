import java.util.regex.Pattern

import com.earldouglas.xwp.XwpPlugin._
import com.mojolly.scalate.ScalatePlugin.ScalateKeys._
import com.mojolly.scalate.ScalatePlugin._
import com.slidingautonomy.sbt.filter.Import._
import com.typesafe.sbt.web.Import.WebKeys._
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.SbtWeb
import org.scalatra.sbt._
import org.scalatra.sbt.DistPlugin._
// import org.scalatra.sbt.DistPlugin.DistKeys._
import sbt.Keys._
import sbt._

object ScalatraSbtwebBuild extends Build {
  val Organization = "org.scalatra"
  val Name = "scalatra-sbtweb"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.4"
  val ScalatraVersion = "2.4.0.M2"


  val webSettings =     Seq(
    webappSrc in webapp := (resourceDirectory in Assets).value,
    // webappDest in webapp := stagingDirectory.value,   // in webapp scope is not respected by dist (!)
    webappDest := stagingDirectory.value,
    includeFilter in filter := "*.less" || "*.css.map",
    pipelineStages := Seq(filter)
  )

  val mySettings =
    ScalatraPlugin.scalatraSettings ++
    DistPlugin.distSettings ++
    scalateSettings ++
    //    tomcat() ++                                           // use tomcat as development container
    //    jetty() ++                                           // use jetty as development container
    Seq(
    scalateTemplateConfig in Compile := Seq(
      TemplateConfig(
        (sourceDirectory in Compile).value / "webapp" / "WEB-INF" / "templates",
        Seq.empty, /* default imports should be added here */
        Seq.empty, /* add extra bindings here */
        Some("templates")
      )
    )
    ) ++
    Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.2.3.v20140905",
        "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
      )
    )

lazy val project = Project("scalatra-sbtweb", file("."))
  .settings(mySettings:_*)
  .settings(webSettings:_*)
  .enablePlugins(SbtWeb)
}
