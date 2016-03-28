package by.dev.madhead.kosgi.alternativedictionary

import by.dev.madhead.kosgi.dictionary.DictionaryService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import java.util.*

class Activator : BundleActivator {
    @Throws(Exception::class)
    override fun start(context: BundleContext) {
        context.registerService(DictionaryService::class.java, object : DictionaryService {
            override fun checkWord(word: String): Boolean {
                return word.toLowerCase() in arrayOf("дарт", "вейдер")
            }
        }, Hashtable(mapOf("Language" to "Russian")))
    }

    @Throws(Exception::class)
    override fun stop(context: BundleContext) {
    }
}
