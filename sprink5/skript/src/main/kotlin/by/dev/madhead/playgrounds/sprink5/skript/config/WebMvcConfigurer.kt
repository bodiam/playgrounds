package by.dev.madhead.playgrounds.sprink5.skript.config

import by.dev.madhead.playgrounds.sprink5.skript.web.BreakingBadCharactersController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver

@Configuration
@SpringBootApplication
@ComponentScan(basePackageClasses = arrayOf(BreakingBadCharactersController::class))
open class WebMvcConfigurer : WebMvcConfigurerAdapter() {
    @Bean
    open fun kotlinScriptConfigurer(): ScriptTemplateConfigurer {
        val configurer = ScriptTemplateConfigurer()

        configurer.engineName = "kotlin"
        configurer.setScripts("scripts/render.kts")
        configurer.renderFunction = "render"
        configurer.isSharedEngine = false

        return configurer
    }

    @Bean
    open fun kotlinScriptViewResolver(): ViewResolver {
        val viewResolver = ScriptTemplateViewResolver()

        viewResolver.setPrefix("templates/")
        viewResolver.setSuffix(".kts")

        return viewResolver
    }
}