package git.junny.kotlinsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class KotlinSecurityApplication

fun main(args: Array<String>) {
    runApplication<KotlinSecurityApplication>(*args)
}
