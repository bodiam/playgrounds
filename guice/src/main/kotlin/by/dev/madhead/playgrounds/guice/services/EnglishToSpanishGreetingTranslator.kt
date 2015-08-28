package by.dev.madhead.playgrounds.guice.services

import com.google.inject.Inject
import com.google.inject.name.Named

class EnglishToSpanishGreetingTranslator @Inject constructor(
        @Named("english") val englishGreeter: Greeter,
        @Named("spanish") val spanishGreeter: Greeter
) {
    fun translate(name: String): String {
        return "English '${englishGreeter.greet(name)}' is '${spanishGreeter.greet(name)}' in Spanish"
    }
}
