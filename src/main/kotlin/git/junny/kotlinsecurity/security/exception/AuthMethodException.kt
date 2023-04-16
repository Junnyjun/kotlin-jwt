package git.junny.kotlinsecurity.security.exception

import org.springframework.security.authentication.AuthenticationServiceException

class AuthMethodException(msg: String): AuthenticationServiceException(msg) {

}