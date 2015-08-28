package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.EnglishGreeter
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import java.util.Properties
import kotlin.reflect.jvm.java

class Module10 : AbstractModule() {
    override fun configure() {
        val properties = Properties();

        properties.load(Module10::class.java.getResourceAsStream("/some.properties"))
        Names.bindProperties(binder(), properties)
        bind(Greeter::class.java).to(EnglishGreeter::class.java)
    }
}
