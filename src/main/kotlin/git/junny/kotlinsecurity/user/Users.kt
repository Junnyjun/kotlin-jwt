package git.junny.kotlinsecurity.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
class Users(
    username: String,
    email: String,
    userPw: String,
    roles: Role
) :  UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(unique = true)
    private val username: String = username

    @Column(nullable = false, unique = true)
    val email: String = email

    @Column(nullable = false)
    val userPw: String = userPw

    @Column(nullable = false)
    var roles: Role = roles

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return mutableListOf(SimpleGrantedAuthority(roles.name));
    }

    override fun getPassword(): String {
        return userPw
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun encode(newPw: String): Users {
        return Users(username, email, newPw, roles)
    }


    enum class Role {
        USER, ADMIN;
    }
}