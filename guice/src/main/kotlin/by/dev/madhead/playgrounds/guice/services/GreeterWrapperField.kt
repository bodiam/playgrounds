package by.dev.madhead.playgrounds.guice.services

import com.google.inject.Inject

class GreeterWrapperField {
    @Inject var greeter: Greeter? = null

    fun doWork(name: String): String {
        return "[${greeter?.greet(name)}] (${greeter})"
    }
}
