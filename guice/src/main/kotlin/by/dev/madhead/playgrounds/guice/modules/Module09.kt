package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.EnglishGreeter
import com.google.inject.AbstractModule
import com.google.inject.Singleton
import kotlin.reflect.jvm.java

class Module09 : AbstractModule() {
    override fun configure() {
        bind(Greeter::class.java).to(EnglishGreeter::class.java).`in`(Singleton::class.java)
    }
}
