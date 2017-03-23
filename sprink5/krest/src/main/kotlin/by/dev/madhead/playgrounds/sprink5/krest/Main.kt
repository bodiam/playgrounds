package by.dev.madhead.playgrounds.sprink5.krest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.getForObject
import java.net.URI

@Configuration
open class JacksonConfiguration {
    @Bean
    open fun objectMapper(): Jackson2ObjectMapperBuilder {
        val result: Jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder()

        result.findModulesViaServiceLoader(true)

        return result
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Response(
        val args: Map<String, String>,
        val headers: Map<String, String>,
        val origin: String,
        val url: String
)

@SpringBootApplication(exclude = arrayOf(
        ServletWebServerFactoryAutoConfiguration::class,
        WebMvcAutoConfiguration::class
))
open class Application : CommandLineRunner {
    override fun run(vararg args: String) {
        val restTemplate = RestTemplate()

        println(restTemplate.getForObject<Response>("http://httpbin.org/get"))
        println(restTemplate.getForObject<Response>(
                "http://httpbin.org/get?param1={param1Value}", "value1"
        ))
        println(restTemplate.getForObject<Response>(
                "http://httpbin.org/get?param2={param2Value}&param1={param1Value}",
                mapOf("param1Value" to "value1", "param2Value" to "value2")
        ))
        println(restTemplate.getForObject<Response>(URI("http://httpbin.org/get")))
        println(restTemplate.getForEntity<String>("http://httpbin.org/get"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
