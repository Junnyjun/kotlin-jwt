package git.junny.kotlinsecurity.user.service

import git.junny.kotlinsecurity.user.UserRepository
import git.junny.kotlinsecurity.user.Users
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserFindService(private val userRepository: UserRepository) {
    fun findUserById(id: Long)=  userRepository.findByIdOrNull(id) ?: error("User not found")
}