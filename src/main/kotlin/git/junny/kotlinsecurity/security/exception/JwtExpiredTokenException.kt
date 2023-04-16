package git.junny.kotlinsecurity.security.exception

import org.springframework.security.core.AuthenticationException


class JwtExpiredTokenException(msg: String, t: Throwable) : AuthenticationException(msg, t){

}
