package org.security.basics

import jakarta.persistence.*

@Entity
@Table(name = "happy_users")
data class HappyUserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Version
    var version: Long? = null,
    var username: String = "",
    var password: String = "",
    var authority: String = "",
    var age: Int = 0,
)