package by.dev.madhead.kosgi.dictionaryclient

import by.dev.madhead.kosgi.dictionary.DictionaryService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

class Activator : BundleActivator {
    override fun start(context: BundleContext?) {
        val serviceReferences = context!!.getServiceReferences(DictionaryService::class.java, "(Language=*)")

        serviceReferences.forEach {
            println(it)
        }

        if ((null != serviceReferences) && (serviceReferences.size > 0)) {
            val dictionary = context.getService(serviceReferences.first())

            println(dictionary.checkWord("darth"))
            println(dictionary.checkWord("вейдер"))
            println(dictionary.checkWord("anakin"))
        }
    }

    override fun stop(context: BundleContext?) {
    }
}