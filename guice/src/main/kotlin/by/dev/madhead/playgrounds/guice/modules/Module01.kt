package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.EnglishGreeter
import com.google.inject.AbstractModule
import kotlin.reflect.jvm.java

class Module01 : AbstractModule() {
    override fun configure() {
        // Simple binding
        bind(Greeter::class.java).to(EnglishGreeter::class.java)

        // This will fail with "binding was already configured"
        // bind(Greeter::class.java).to(SpanishGreeter::class.java)
    }
}
