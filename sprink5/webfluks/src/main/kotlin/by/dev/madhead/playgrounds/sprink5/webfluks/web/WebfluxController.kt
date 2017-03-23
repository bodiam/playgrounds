package by.dev.madhead.playgrounds.sprink5.webfluks.web

import by.dev.madhead.playgrounds.sprink5.webfluks.model.Character
import by.dev.madhead.playgrounds.sprink5.webfluks.model.Movie
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import toFlux
import toMono
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/controller")
class WebfluxController(
        val reactiveMongoTemplate: ReactiveMongoTemplate
) {
    @GetMapping("basicMono")
    fun basicMono() = "basicMono".toMono()

    @GetMapping("callableMono")
    fun callableMono() = Callable<String> { "callableMono" }.toMono()

    @GetMapping("completableFutureMono")
    fun completableFutureMono() = CompletableFuture.supplyAsync { "completableFutureMono" }.toMono()

    @GetMapping("throwableMono")
    fun throwableMono() = NullPointerException("throwableMono").toMono<String>()

    @GetMapping("arrayFlux")
    fun arrayFlux() = arrayOf("foo", "bar", "baz").toFlux()

    @GetMapping("iterableFlux")
    fun iterableFlux() = (1..10).toFlux()

    @GetMapping("throwableFlux")
    fun throwableFlux() = NullPointerException("throwableFlux").toFlux<String>()

    @GetMapping("operations")
    fun movies(): Mono<MutableMap<String, List<String>>> {
        return reactiveMongoTemplate
                .findAll(Character::class.java)
                // Flux<Character>
                .flatMap { character ->
                    reactiveMongoTemplate.find(
                            Query().apply {
                                addCriteria(Criteria.where("characters").all(character.name))
                            },
                            Movie::class.java
                    )
                            .map { it.title }
                            .collectList()
                            .map { character.name to it }
                }
                .collect({
                    mutableMapOf<String, List<String>>()
                }, { container, element ->
                    container[element.first] = element.second
                })
    }
}
