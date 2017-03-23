package by.dev.madhead.playgrounds.sprink5.webfluks

import by.dev.madhead.playgrounds.sprink5.webfluks.model.Character
import by.dev.madhead.playgrounds.sprink5.webfluks.model.Movie
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.data.mongo.ReactiveMongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.junit4.SpringRunner
import reactor.test.StepVerifier

@RunWith(SpringRunner::class)
@SpringBootTest(
        classes = arrayOf(
                MongoAutoConfiguration::class,
                MongoDataAutoConfiguration::class,
                ReactiveMongoAutoConfiguration::class,
                ReactiveMongoDataAutoConfiguration::class
        )
)
class MoviesFluxTest {
    @Autowired
    lateinit var reactiveMongoTemplate: ReactiveMongoTemplate

    @Test
    fun moviesFluxTest() {
        val flux = reactiveMongoTemplate
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
                .log()

        StepVerifier
                .create(flux)
                .expectNextCount(1)
                .verifyComplete()
    }
}
