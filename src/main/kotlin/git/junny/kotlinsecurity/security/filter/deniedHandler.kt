package git.junny.kotlinsecurity.security.filter

import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.access.AccessDeniedHandlerImpl
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class deniedHandler : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: org.springframework.security.access.AccessDeniedException?
    ) {
        println("Access Denied")
        response?.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
        return AccessDeniedHandlerImpl().handle(request, response, accessDeniedException)
    }
}