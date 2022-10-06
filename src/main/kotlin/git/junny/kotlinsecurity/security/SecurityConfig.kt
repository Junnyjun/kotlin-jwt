package git.junny.kotlinsecurity.security

import git.junny.kotlinsecurity.security.component.JwtAuthenticate
import git.junny.kotlinsecurity.security.component.JwtToken
import git.junny.kotlinsecurity.security.component.JwtTokenByRequest
import git.junny.kotlinsecurity.security.filter.JwtValidateFilter
import git.junny.kotlinsecurity.security.filter.deniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenByRequest: JwtTokenByRequest,
    private val jwtToken: JwtToken,
    private val jwtAuthenticate: JwtAuthenticate,
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http.
        httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/auth").authenticated()
            .antMatchers("/**/register", "/**/login","/login/**", "/logout/**").permitAll()
            .and()
            .addFilterBefore(JwtValidateFilter(jwtToken, jwtAuthenticate, jwtTokenByRequest), UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling().accessDeniedHandler(deniedHandler())
    }
}