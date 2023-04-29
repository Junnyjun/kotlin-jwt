package git.junny.kotlinsecurity.security.filter

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.util.matcher.RequestMatcher
import java.util.Objects.isNull
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtTokenAuthenticationFilter(matcher: RequestMatcher?, failureHandler: AuthenticationFailureHandler?) :
    AbstractAuthenticationProcessingFilter(matcher) {
    init {
        setAuthenticationFailureHandler(failureHandler)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val tokenPayload = extractToken(request.getHeader(HttpHeaders.AUTHORIZATION))
        return authenticationManager.authenticate(JwtAuthenticationToken(tokenPayload))
    }

    override fun successfulAuthentication(
        request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain,
        authentication: Authentication
    ) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authentication
        SecurityContextHolder.setContext(context)
        chain.doFilter(request, response)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest, response: HttpServletResponse,
        authenticationException: AuthenticationException
    ) {
        SecurityContextHolder.clearContext()
        failureHandler.onAuthenticationFailure(request, response, authenticationException)
    }

    private fun extractToken(tokenPayload: String): String {
        if (isNull(tokenPayload) || !tokenPayload.startsWith("Bearer ")) {
            throw BadCredentialsException("Invalid token")
        }
        return tokenPayload.replace("Bearer ", "")
    }
}

