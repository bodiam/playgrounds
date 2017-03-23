package by.dev.madhead.playgrounds.sprink5.webfluksdsl

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import toFlux
import toMono
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.experimental.buildIterator

@SpringBootApplication
open class DemoApplication {
    @Bean
    open fun routes() = router {
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

            (GET("/throwableMono/**") and pathExtension("pdf")) {
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

            GET("/echo") {
                ServerResponse
                        .ok()
                        .body(it.queryParam("echo").orElseGet { "hear no evil" }.toMono(), String::class.java)
            }

            GET("/fibonnaci1", this@DemoApplication::fibonnaci1)

            GET("/fibonnaci2", this@DemoApplication::fibonnaci2)
        }
    }

    fun fibonnaci1(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .body(
                        Flux.generate(
                                { arrayOf(0, 1) }, // Not thread-safe
                                { state: Array<Int>, sink: SynchronousSink<String> ->
                                    sink.next("Next Fibonnaci number: ${state[0]}\n")

                                    val tmp = state[1]

                                    state [1] += state[0]
                                    state[0] = tmp
                                    state
                                }
                        ).take(10),
                        String::class.java
                )
    }

    fun fibonnaci2(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .body(
                        buildIterator {
                            yield("Next Fibonnaci number: 0\n")
                            var cur = 0
                            var next = 1
                            while (true) {
                                yield("Next Fibonnaci number: ${next}\n")
                                val tmp = cur + next
                                cur = next
                                next = tmp
                            }
                        }.toFlux().take(10),
                        String::class.java
                )
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}
