package git.junny.kotlinsecurity.user.service

import git.junny.kotlinsecurity.user.UserRepository
import git.junny.kotlinsecurity.user.Users
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSaveService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(user: Users) {
        userRepository.save(user)
    }
}