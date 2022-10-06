package git.junny.kotlinsecurity.web

import git.junny.kotlinsecurity.security.component.JwtToken
import git.junny.kotlinsecurity.user.Users
import git.junny.kotlinsecurity.user.service.UserFindService
import git.junny.kotlinsecurity.user.service.UserSaveService
import org.apache.catalina.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserHttpRestController (
    private val userSaveService: UserSaveService,
    private val userFindService: UserFindService,
    private val jwtToken: JwtToken,
    private val passwordEncoder: PasswordEncoder
        ) {

    @PostMapping("/register")
    fun register(
        @RequestBody user: Users
    ) {
        userSaveService.saveUser(user);
    }
    @PostMapping("/login")
    fun login(@RequestBody userLoginReq: UserLoginReq): ResponseEntity<String> {
        val user: Users =  userFindService.findUserById(userLoginReq.username)

        if(!passwordEncoder.matches(userLoginReq.password, user.password)) {
            throw IllegalArgumentException("Wrong password")
        }

        return ResponseEntity.ok(jwtToken.createToken(userLoginReq.username))
    }
    @GetMapping("/auth")
    fun login(
        @AuthenticationPrincipal user: Users
    ): ResponseEntity<String> {
        println(user);
        return ResponseEntity.ok("success")
    }
    class UserLoginReq(
        val username: String,
        val password: String
    )
}