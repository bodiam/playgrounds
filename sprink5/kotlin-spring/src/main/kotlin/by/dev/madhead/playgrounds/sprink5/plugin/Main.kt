package by.dev.madhead.playgrounds.sprink5.plugin

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
class DeepThoughtConfiguration {
    @Bean
    @Qualifier("answer")
    fun answer() = 42
}

@SpringBootApplication
@Import(
        DeepThoughtConfiguration::class
)
class Application(
        @Qualifier("answer") val answer: Int
) : CommandLineRunner {
    override fun run(vararg args: String) {
        println("The Answer to the Ultimate Question of Life, The Universe, and Everything is ${answer}")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
