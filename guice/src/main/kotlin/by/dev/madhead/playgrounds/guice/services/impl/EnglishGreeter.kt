package by.dev.madhead.playgrounds.guice.services.impl

import by.dev.madhead.playgrounds.guice.services.Greeter

open class EnglishGreeter : Greeter {
    protected open val greeting: String = "Hello,"

    override fun greet(name: String): String {
        return "${greeting} ${name}!"
    }
}
