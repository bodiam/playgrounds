package by.dev.madhead.playgrounds.sprink5.skript

import by.dev.madhead.playgrounds.sprink5.skript.config.WebMvcConfigurer
import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebMvcConfigurer::class.java, *args)
}
