package by.dev.madhead.playgrounds.guice.services.impl

import by.dev.madhead.playgrounds.guice.services.Greeter

class SpanishGreeter : Greeter {
    override fun greet(name: String): String {
        return "?Hola, ${name}!"
    }
}
