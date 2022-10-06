package git.junny.kotlinsecurity.user.service

import git.junny.kotlinsecurity.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val userRepository: UserRepository
) : UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
            ?: error("User not found")
    }
}