package by.dev.madhead.playgrounds.guice

import by.dev.madhead.playgrounds.guice.modules.*
import by.dev.madhead.playgrounds.guice.services.*
import by.dev.madhead.playgrounds.guice.services.impl.EnglishGreeter
import by.dev.madhead.playgrounds.guice.services.impl.MedievalEnglishGreeter
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Key
import com.google.inject.Stage
import com.google.inject.name.Names
import kotlin.reflect.jvm.java

public fun main(args: Array<String>): Unit {
    val injector01 = Guice.createInjector(Module01())

    assertedPrintln(injector01.getInstance(Greeter::class.java).greet("madhead"), "Hello, madhead!")

    val injector02 = Guice.createInjector(Module02())

    assertedPrintln(injector02.getInstance(Greeter::class.java).greet("madhead"), "Good day, sir madhead!")
    assertedPrintln(injector02.getInstance(EnglishGreeter::class.java).greet("madhead"), "Good day, sir madhead!")
    assertedPrintln(injector02.getInstance(MedievalEnglishGreeter::class.java).greet("madhead"), "Good day, sir madhead!")

    val injector03 = Guice.createInjector(Module03())

    assertedPrintln(injector03.getInstance(Key.get(Greeter::class.java, Names.named("english"))).greet("madhead"), "Hello, madhead!")
    assertedPrintln(injector03.getInstance(Key.get(Greeter::class.java, Names.named("spanish"))).greet("madhead"), "?Hola, madhead!")
    assertedPrintln(injector03.getInstance(EnglishToSpanishGreetingTranslator::class.java).translate("madhead"), "English 'Hello, madhead!' is '?Hola, madhead!' in Spanish")
    assertedPrintln(injector03.getInstance(SpanishToEnglishGreetingTranslator::class.java).translate("madhead"), "Spanish '?Hola, madhead!' is 'Hello, madhead!' in English")

    val injector04 = Guice.createInjector(Module04())

    assertedPrintln(injector04.getInstance(Greeter::class.java).greet("madhead"), "Witaj, madhead!")

    val injector05 = Guice.createInjector(Module05())

    assertedPrintln(injector05.getInstance(Greeter::class.java).greet("madhead"), "Здраво, madhead!")
    assertedPrintln(injector05.getInstance(Key.get(Greeter::class.java, Names.named("chroatian"))).greet("madhead"), "Bok, madhead!")

    val injector06 = Guice.createInjector(Module06())

    assertedPrintln(injector06.getInstance(Greeter::class.java).greet("madhead"), "Salve, madhead!")

    val injector07 = Guice.createInjector(Module07())

    assertedPrintln(injector07.getInstance(Greeter::class.java).greet("madhead"), "Robot says: Hello, madhead!")

    val injector08 = Guice.createInjector(Module08())

    println(injector08.getInstance(Injector::class.java))
    println(injector08.getInstance(Stage::class.java))
    println(Guice.createInjector().getInstance(Stage::class.java))

    val injector09 = Guice.createInjector(Module09())

    println(injector09.getInstance(GreeterWrapperConstructor::class.java).doWork("madhead"))
    println(injector09.getInstance(GreeterWrapperField::class.java).doWork("madhead"))
    println(injector09.getInstance(GreeterWrapperSetter::class.java).doWork("madhead"))

    val injector10 = Guice.createInjector(Module10())

    assertedPrintln(injector10.getInstance(Greeter::class.java).greet(injector10.getInstance(Key.get(String::class.java, Names.named("name")))), "Hello, madhead!")
}

fun assertedPrintln(actual: Any, expected: Any) {
    println(actual)

    if (actual != expected) {
        throw AssertionError("[${actual}] expected to be [${expected}]")
    }
}
