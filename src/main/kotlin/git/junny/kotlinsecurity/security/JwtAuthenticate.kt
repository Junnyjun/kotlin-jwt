package git.junny.kotlinsecurity.security

import git.junny.kotlinsecurity.user.service.UserFindService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthenticate(
    private val userFindService: UserFindService,
    @Value("\${secrets.keys}") private var secretKey: String,
) {
    fun decode(token: String): Authentication {
        val users = userFindService.findUserById(parseUserId(token).toLong())
        return UsernamePasswordAuthenticationToken(users, "", users.authorities)
    }

    private fun parseUserId(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }
}