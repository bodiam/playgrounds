import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LocalArtifactoryScenario extends Simulation {
  val httpConf = http
    .baseURL("http://localhost:7373/artifactory")
    .inferHtmlResources(BlackList( """.*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0")

  // I know that this scenario tests caches and not the app, but it's just a snippet.
  val scn = scenario("User")
    .exec(http("Load home page").get("/"))
    .pause(1 seconds, 3 seconds)
    .exec(http("Browse artifacts").get("/webapp/browserepo.html"))
    .pause(3 seconds, 7 seconds)
    .exec(http("Browse builds").get("/webapp/builds"))
    .pause(1 seconds)
    .exec(http("Quick search page").get("/webapp/search/artifact"))
    .pause(1 seconds, 6 seconds)
    .exec(http("Search artifact").post("/webapp/search/artifact").formParam("query", "commons-lang"))

  setUp(scn.inject(
    (1 until 3).flatMap { i =>
      List(
        rampUsersPerSec(1) to i * 10 during (10 seconds) randomized,
        nothingFor(10 seconds)
      )
    }
  )).protocols(httpConf)
}
