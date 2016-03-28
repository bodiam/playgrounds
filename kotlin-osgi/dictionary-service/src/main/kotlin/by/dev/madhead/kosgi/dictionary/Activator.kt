package by.dev.madhead.kosgi.dictionary

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import java.util.*

class Activator : BundleActivator {
    @Throws(Exception::class)
    override fun start(context: BundleContext) {
        context.registerService(DictionaryService::class.java, object : DictionaryService {
            override fun checkWord(word: String): Boolean {
                return word.toLowerCase() in arrayOf("darth", "vader")
            }
        }, Hashtable(mapOf("Language" to "English")))
    }

    @Throws(Exception::class)
    override fun stop(context: BundleContext) {
    }
}
