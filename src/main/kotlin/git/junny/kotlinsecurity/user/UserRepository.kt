package git.junny.kotlinsecurity.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Long> {
    fun findByUsername(user: String) : Users?
}