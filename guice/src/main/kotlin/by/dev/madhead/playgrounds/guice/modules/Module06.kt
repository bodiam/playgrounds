package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import com.google.inject.AbstractModule
import com.google.inject.Provider
import kotlin.reflect.jvm.java

class Module06 : AbstractModule() {
    override fun configure() {
        // Bind to an instance of Provider. Class can be bound to too
        bind(Greeter::class.java).toProvider (object : Provider<Greeter> {
            override fun get(): Greeter {
                return object : Greeter {
                    override fun greet(name: String): String {
                        return "Salve, ${name}!"
                    }
                }
            }
        })
    }
}
