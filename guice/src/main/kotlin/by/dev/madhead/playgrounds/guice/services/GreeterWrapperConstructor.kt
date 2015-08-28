package by.dev.madhead.playgrounds.guice.services

import com.google.inject.Inject

class GreeterWrapperConstructor @Inject constructor(
        val greeter: Greeter
) {
    fun doWork(name: String): String {
        return "[${greeter.greet(name)}] (${greeter})"
    }
}
