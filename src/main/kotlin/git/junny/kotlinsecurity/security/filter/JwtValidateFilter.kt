package git.junny.kotlinsecurity.security.filter

import git.junny.kotlinsecurity.security.component.JwtAuthenticate
import git.junny.kotlinsecurity.security.component.JwtToken
import git.junny.kotlinsecurity.security.component.JwtTokenByRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtValidateFilter(
    private val jwtToken: JwtToken,
    private val jwtAuthenticate: JwtAuthenticate,
    private val jwtTokenByRequest: JwtTokenByRequest) : GenericFilterBean(){

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token: String? = jwtTokenByRequest.resolveToken((request as HttpServletRequest))
        if (token != null && jwtToken.validate(token)) {
            val authentication = jwtAuthenticate.decode(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain?.doFilter(request, response)
    }
}