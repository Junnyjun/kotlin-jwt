package git.junny.kotlinsecurity.security.component

import git.junny.kotlinsecurity.user.service.UserClaimsService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtToken(
    private val userDetailsService: UserDetailsService,
    private val userClaimService: UserClaimsService,
    @Value("\${secrets.keys}") private val secretKey: String,
    @Value("\${secrets.time}") private val expiredTime: Long,
) {

    fun createToken(username : String) :String{
        return Jwts.builder()
            .setClaims(userClaimService.toClaims(username))
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + expiredTime.toLong()))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }
    fun validate(jwtToken: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}