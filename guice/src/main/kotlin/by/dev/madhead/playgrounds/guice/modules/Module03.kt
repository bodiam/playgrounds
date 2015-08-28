package by.dev.madhead.playgrounds.guice.modules

import by.dev.madhead.playgrounds.guice.annotations.English
import by.dev.madhead.playgrounds.guice.annotations.Spanish
import by.dev.madhead.playgrounds.guice.services.Greeter
import by.dev.madhead.playgrounds.guice.services.impl.EnglishGreeter
import by.dev.madhead.playgrounds.guice.services.impl.SpanishGreeter
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kotlin.reflect.jvm.java

class Module03 : AbstractModule() {
    override fun configure() {
        // Qualifiers
        bind(Greeter::class.java).annotatedWith(Names.named("english")).to(EnglishGreeter::class.java)
        bind(Greeter::class.java).annotatedWith(Names.named("spanish")).to(SpanishGreeter::class.java)

        // Also available as custom annotations
        bind(Greeter::class.java).annotatedWith(javaClass<English>()).to(EnglishGreeter::class.java)
        bind(Greeter::class.java).annotatedWith(javaClass<Spanish>()).to(SpanishGreeter::class.java)

        // No bindings for EnglishToSpanishGreetingTranslator and SpanishToEnglishGreetingTranslator - they are bound automatically due to bind-eligible constructors
    }
}
