package by.dev.madhead.kosgi.servicelistener

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceEvent
import org.osgi.framework.ServiceListener

class Activator : BundleActivator, ServiceListener {
    @Throws(Exception::class)
    override fun start(context: BundleContext) {
        println("Starting Kotlin-OSGi Service Listener")
        context.addServiceListener(this)
    }

    @Throws(Exception::class)
    override fun stop(context: BundleContext) {
        context.removeServiceListener(this)
        println("Stopping Kotlin-OSGi Service Listener")
    }

    override fun serviceChanged(event: ServiceEvent) {
        val objectClass = event.serviceReference.getProperty("objectClass") as Array<String>

        when (event.type) {
            ServiceEvent.REGISTERED -> println("REGISTERED ${objectClass[0]}")
            ServiceEvent.UNREGISTERING -> println("UNREGISTERING ${objectClass[0]}")
            ServiceEvent.MODIFIED -> println("MODIFIED ${objectClass[0]}")
            ServiceEvent.MODIFIED_ENDMATCH -> println("MODIFIED ${objectClass[0]}")
        }
    }
}
