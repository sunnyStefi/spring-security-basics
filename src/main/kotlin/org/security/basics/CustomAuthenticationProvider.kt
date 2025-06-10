package org.security.basics


import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder

class CustomAuthenticationProvider(userDetailsService: UserDetailsService, passwordEncoder: PasswordEncoder) : AuthenticationProvider {

    private val passwordEncoder: PasswordEncoder = passwordEncoder //IS THIS CORRECT?
    private val userDetailsService: UserDetailsService = userDetailsService

    override fun authenticate(authentication: Authentication): Authentication {
        val username: String = authentication.name
        val password: String = authentication.credentials.toString()

        val userDetails = this.userDetailsService.loadUserByUsername(username)

        if (passwordEncoder.matches(password, userDetails.getPassword()))
            return UsernamePasswordAuthenticationToken(username, password, userDetails.authorities)
        else throw BadCredentialsException("User not found")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}