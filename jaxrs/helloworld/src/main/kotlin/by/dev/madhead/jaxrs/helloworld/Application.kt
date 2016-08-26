package by.dev.madhead.jaxrs.helloworld

import javax.ws.rs.ApplicationPath

@ApplicationPath("rest")
class Application : javax.ws.rs.core.Application() {
    override fun getClasses() = setOf(Books::class.java)
}
