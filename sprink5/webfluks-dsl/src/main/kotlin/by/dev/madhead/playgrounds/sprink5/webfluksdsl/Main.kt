package by.dev.madhead.playgrounds.sprink5.webfluksdsl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import toFlux
import toMono
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

@SpringBootApplication
open class DemoApplication {
    @Bean
    open fun routes(
            @Autowired reactiveMongoTemplate: ReactiveMongoTemplate
    ) = router {
        resources("/**", ClassPathResource("/"))

        "/controller".nest {
            (GET("/basicMono") and accept(MediaType.ALL)) {
                ServerResponse
                        .ok()
                        .body("basicMono".toMono(), String::class.java)
            }

            (GET("/callableMono") and headers { it.header("X-REQUIRED-HEADER").isNotEmpty() }) {
                ServerResponse
                        .ok()
                        .body(Callable<String> { "callableMono" }.toMono(), String::class.java)
            }

            (GET("/completableFutureMono") and queryParam("requiredQueryParam", { it.contains("secret") })) {
                ServerResponse
                        .ok()
                        .body(CompletableFuture.supplyAsync { "completableFutureMono" }.toMono(), String::class.java)
            }

            (GET("/throwableMono") and pathExtension("pdf")) {
                ServerResponse
                        .ok()
                        .body(NullPointerException("throwableMono").toMono<String>(), String::class.java)
            }

            GET("/arrayFlux") {
                ServerResponse
                        .ok()
                        .body(arrayOf("foo", "bar", "baz").toFlux(), String::class.java)
            }

            GET("/iterableFlux") {
                ServerResponse
                        .ok()
                        .body((1..10).toFlux(), Int::class.java)
            }

            GET("/throwableFlux") {
                ServerResponse
                        .ok()
                        .body(NullPointerException("throwableFlux").toFlux<String>(), String::class.java)
            }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}
