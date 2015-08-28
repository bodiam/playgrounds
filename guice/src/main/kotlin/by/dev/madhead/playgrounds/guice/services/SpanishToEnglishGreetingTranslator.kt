package by.dev.madhead.playgrounds.guice.services

import by.dev.madhead.playgrounds.guice.annotations.English
import by.dev.madhead.playgrounds.guice.annotations.Spanish
import com.google.inject.Inject

class SpanishToEnglishGreetingTranslator @Inject constructor(
        @English val englishGreeter: Greeter,
        @Spanish val spanishGreeter: Greeter
) {
    fun translate(name: String): String {
        return "Spanish '${spanishGreeter.greet(name)}' is '${englishGreeter.greet(name)}' in English"
    }
}
