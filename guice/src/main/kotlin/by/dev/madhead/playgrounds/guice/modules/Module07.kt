package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.NamedGreeter
import com.google.inject.AbstractModule
import kotlin.reflect.jvm.java

class Module07 : AbstractModule() {
    override fun configure() {
        // NamedGreeter will be injected with "Robot" without any annotations
        bind(Greeter::class.java).toConstructor(NamedGreeter::class.java.getConstructor(String::class.java))
        bind(String::class.java).toInstance("Robot")
    }
}
