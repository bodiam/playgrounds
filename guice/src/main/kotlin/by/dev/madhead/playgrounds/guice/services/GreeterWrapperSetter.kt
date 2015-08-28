package by.dev.madhead.playgrounds.guice.services

import com.google.inject.Inject
import kotlin.properties.Delegates

class GreeterWrapperSetter {
    var greeter: Greeter by Delegates.notNull()
        @Inject set

    fun doWork(name: String): String {
        return "[${greeter.greet(name)}] (${greeter})"
    }
}
