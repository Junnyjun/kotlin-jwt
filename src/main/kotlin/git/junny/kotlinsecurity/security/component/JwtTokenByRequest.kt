package git.junny.kotlinsecurity.security.component

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenByRequest {
    fun resolveToken(request: HttpServletRequest) = request.getHeader("Authorization") ?: ""

}