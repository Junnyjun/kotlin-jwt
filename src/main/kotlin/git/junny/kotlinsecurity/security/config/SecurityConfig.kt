package git.junny.kotlinsecurity.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity


private const val AUTHENTICATION_URL = "/api/auth/login"
private const val API_ROOT_URL = "/api/**"

@EnableWebSecurity
class SecurityConfig(
    private val mapper:ObjectMapper
) {
}