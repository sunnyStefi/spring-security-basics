package org.security.basics.config

import org.security.basics.HappyUser
import org.security.basics.HappyUserEntity
import org.security.basics.HappyUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class UserConfig {
    @Bean
    fun initUsers(repository: HappyUserRepository, encoder: PasswordEncoder): CommandLineRunner = CommandLineRunner {
        if (repository.count() == 0L) {
            val savedUsers = repository.saveAll(
                listOf(
                    HappyUserEntity(null,1, "giggle_panda", encoder.encode("bamboo"), "ROLE_USER"),
                    HappyUserEntity(null,1, "tickle_unicorn", encoder.encode("sparkles"), "ROLE_USER")
                )
            )
            println("Saved users: $savedUsers")
        }
    }
    @Bean
    fun loadUserByUsername(happyUserRepository: HappyUserRepository): UserDetailsService {
        return UserDetailsService { username ->
            val userEntity = happyUserRepository.findByUsername(username)

            if (userEntity != null) {
                HappyUser(userEntity)
            } else {
                throw UsernameNotFoundException("User not found: $username")
            }
        }
    }


    @Bean
    fun encoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }


}