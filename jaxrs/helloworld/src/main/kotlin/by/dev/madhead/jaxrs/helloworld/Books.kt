package by.dev.madhead.jaxrs.helloworld

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("books")
class Books {
    @GET
    fun get() = "Hello from JAX-RS"
}
