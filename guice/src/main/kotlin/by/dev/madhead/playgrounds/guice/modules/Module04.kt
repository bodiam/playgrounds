package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.CustomGreeter
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kotlin.reflect.jvm.java

class Module04 : AbstractModule() {
    override fun configure() {
        // Bind instances
        bind(String::class.java).annotatedWith(Names.named("custom")).toInstance("Witaj,")
        bind(Greeter::class.java).to(CustomGreeter::class.java)
    }
}
