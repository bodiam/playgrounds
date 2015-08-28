package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.name.Named

class Module05 : AbstractModule() {
    override fun configure() {
        // @Provides are used, so nothing here
    }

    // This is 'default' provider
    @Provides
    fun serbianGreeter(): Greeter = object : Greeter {
        override fun greet(name: String): String {
            return "Здраво, ${name}!"
        }
    }

    // Also, qualifiers can be used
    @Provides
    @Named("chroatian")
    fun chroatianGreeter(): Greeter = object : Greeter {
        override fun greet(name: String): String {
            return "Bok, ${name}!"
        }
    }
}
