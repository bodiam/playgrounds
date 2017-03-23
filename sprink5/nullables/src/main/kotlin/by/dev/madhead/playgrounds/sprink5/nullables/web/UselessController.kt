package by.dev.madhead.playgrounds.sprink5.nullables.web

import by.dev.madhead.playgrounds.sprink5.nullables.service.UselessService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/controller")
class UselessController(
        @Qualifier("nullableService1") val nullableService1: UselessService,
        @Qualifier("nullableService2") val nullableService2: UselessService?
) {
    @GetMapping(value = "dependencies", produces = arrayOf(MediaType.TEXT_PLAIN_VALUE))
    fun getOptionalDependencies(): String = "nullableService1: ${nullableService1.doWork()},\nnullableService2: ${nullableService2?.doWork() ?: "null"}"

    @GetMapping(value = "requiredParam", produces = arrayOf(MediaType.TEXT_PLAIN_VALUE))
    fun getRequiredParam(@RequestParam("name") name: String): String = "Hello, ${name}!"

    @GetMapping(value = "optionalParam", produces = arrayOf(MediaType.TEXT_PLAIN_VALUE))
    fun getOptionalParam(@RequestParam("name") name: String?): String = when {
        null == name -> "Hello, world!"
        else -> "Hello, ${name}!"
    }
}
