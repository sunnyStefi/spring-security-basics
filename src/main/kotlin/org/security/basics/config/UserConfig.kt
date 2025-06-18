package org.security.basics.config

import org.security.basics.entity.HappyAuthorityEntity
import org.security.basics.entity.HappyUserEntity
import org.security.basics.repository.HappyAuthorityRepository
import org.security.basics.repository.HappyUserRepository
import org.security.basics.utilities.TimingPasswordEncoder
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource


@Configuration
class UserConfig {

    @Bean
    fun initUsers(userRepository: HappyUserRepository, authorityRepository: HappyAuthorityRepository, encoder: PasswordEncoder): CommandLineRunner = CommandLineRunner {

        userRepository.deleteAll()
        authorityRepository.deleteAll()

        val roleUser = HappyAuthorityEntity(authority = "ROLE_USER")
        val roleAdmin = HappyAuthorityEntity(authority = "ROLE_ADMIN")

        if (authorityRepository.count() == 0L) {
            authorityRepository.saveAll(listOf(roleUser, roleAdmin))
        }

        if (userRepository.count() == 0L) {
            val user = HappyUserEntity(
                username = "giggle_panda",
                password = encoder.encode("bamboo"),
                authorities = mutableSetOf(roleUser),
                enabled = true
            )
            userRepository.save(user)
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
    fun encoder(): PasswordEncoder {
        val encoders: MutableMap<String, PasswordEncoder> = mutableMapOf()
        encoders["classic_encoder"] = BCryptPasswordEncoder(11)
        encoders["flavored_encoder"] = SCryptPasswordEncoder(131072, 16, 2, 64, 32)
        val delegatingPasswordEncoder = DelegatingPasswordEncoder("flavored_encoder", encoders)
        return TimingPasswordEncoder(delegatingPasswordEncoder)
    }

}