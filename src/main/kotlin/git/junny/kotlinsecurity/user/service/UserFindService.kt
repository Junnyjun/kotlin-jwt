package git.junny.kotlinsecurity.user.service

import git.junny.kotlinsecurity.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserFindService(private val userRepository: UserRepository) {
    fun findUserById(id: Long)=  userRepository.findByIdOrNull(id) ?: error("User not found")
    fun findUserById(username: String)=  userRepository.findByUsername(username) ?: error("User not found")
}