package git.junny.kotlinsecurity.user.service

import git.junny.kotlinsecurity.user.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class UserClaimsService(private val userRepository: UserRepository) {
    fun toClaims(username : String): Claims {
        val users = userRepository.findByUsername(username)
            ?: error("User not found")

        return Jwts.claims().setSubject(users.id.toString())
            .apply {
                this["roles"] = users.roles
                this["userId"] = users.id
                this["email"] = users.email
            }
    }
}