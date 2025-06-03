package org.security.basics.config

import org.security.basics.entity.HappyAuthorityEntity
import org.security.basics.entity.HappyUserEntity
import org.security.basics.repository.HappyAuthorityRepository
import org.security.basics.repository.HappyUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.sql.DataSource


@Configuration
class UserConfig {

    @Bean
    fun initUsers(userRepository: HappyUserRepository, authorityRepository: HappyAuthorityRepository, encoder: PasswordEncoder): CommandLineRunner = CommandLineRunner {
        val roleUser = HappyAuthorityEntity(authority = "ROLE_USER")
        val roleAdmin = HappyAuthorityEntity(authority = "ROLE_ADMIN")

        if (authorityRepository.count() == 0L) {
            authorityRepository.saveAll(listOf(roleUser, roleAdmin))
        }

        if (userRepository.count() == 0L) {
            val user1 = HappyUserEntity(
                username = "giggle_panda",
                password = encoder.encode("bamboo"),
                authorities = mutableSetOf(roleUser),
                enabled = true
            )

            val user2 = HappyUserEntity(
                username = "tickle_unicorn",
                password = encoder.encode("sparkles"),
                authorities = mutableSetOf(roleUser, roleAdmin),
                enabled = true
            )
            userRepository.saveAll(listOf(user1, user2))
        }
    }

    @Bean
    fun loadUserByUsername(dataSource: DataSource): UserDetailsService {
        val manager = JdbcUserDetailsManager(dataSource)
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM happy_users WHERE username = ?")
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM happy_user_authorities WHERE username = ?")
        return manager
    }

    @Bean
    fun encoder() : PasswordEncoder {
        return BCryptPasswordEncoder(11);
    }

}