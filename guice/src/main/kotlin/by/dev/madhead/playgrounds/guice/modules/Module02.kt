package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.EnglishGreeter
import by.dev.madhead.playgrounds.guice.services.impl.MedievalEnglishGreeter
import com.google.inject.AbstractModule
import kotlin.reflect.jvm.java

class Module02 : AbstractModule() {
    override fun configure() {
        // Bindings are chained, so if you ask for Greeter, you'll get MedievalEnglishGreeter
        bind(Greeter::class.java).to(EnglishGreeter::class.java)
        bind(EnglishGreeter::class.java).to(MedievalEnglishGreeter::class.java)
    }
}
