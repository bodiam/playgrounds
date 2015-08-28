package by.dev.madhead.playgrounds.guice.services.impl

import by.dev.madhead.playgrounds.guice.services.Greeter
import com.google.inject.Inject
import com.google.inject.name.Named

class CustomGreeter @Inject constructor(
        @Named("custom") val greetingString: String
) : Greeter {
    override fun greet(name: String): String {
        return "${greetingString} ${name}!"
    }
}
