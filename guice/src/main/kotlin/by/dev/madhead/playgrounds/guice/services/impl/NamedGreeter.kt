package by.dev.madhead.playgrounds.guice.services.impl

import by.dev.madhead.playgrounds.guice.services.Greeter

class NamedGreeter constructor(
        val name: String
) : Greeter {
    override fun greet(name: String): String {
        return "${this.name} says: Hello, ${name}!"
    }
}
