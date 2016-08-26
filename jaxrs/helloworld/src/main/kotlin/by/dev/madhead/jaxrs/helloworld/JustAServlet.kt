package by.dev.madhead.jaxrs.helloworld

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "JustAServlet", urlPatterns = arrayOf("/JustAServlet"))
class JustAServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val out = resp!!.writer

        resp.contentType = "text/plain;charset=UTF-8"
        out.println("It's just a servlet")
    }
}
