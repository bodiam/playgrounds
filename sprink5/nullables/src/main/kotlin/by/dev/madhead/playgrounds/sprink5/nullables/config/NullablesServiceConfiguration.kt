package by.dev.madhead.playgrounds.sprink5.nullables.config

import by.dev.madhead.playgrounds.sprink5.nullables.service.UselessService
import by.dev.madhead.playgrounds.sprink5.nullables.web.UselessController
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@SpringBootApplication
@ComponentScan(basePackageClasses = arrayOf(UselessController::class))
open class NullablesServiceConfiguration {
    @Bean
    @Qualifier("nullableService1")
    open fun nullableService1() = UselessService("I do nothing, but at least I exist")
}
