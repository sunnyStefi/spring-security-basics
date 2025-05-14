package org.security.basics

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class HappyUser : UserDetails {
    private lateinit var username: String
    private lateinit var password: String

    //why not @Override
    override fun getUsername(): String {
        return username;
    }

    override fun getPassword(): String {
        return password;
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("READ"))
      //why this does not work in kotlin??  return listOf(() -> "Read"))
    }

    //
}