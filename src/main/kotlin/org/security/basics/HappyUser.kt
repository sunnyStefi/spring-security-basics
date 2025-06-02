package org.security.basics

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class HappyUser(
    private val happyUser: HappyUserEntity
) : UserDetails {

    override fun getUsername(): String = happyUser.username

    override fun getPassword(): String = happyUser.password

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority(happyUser.authority))
}
