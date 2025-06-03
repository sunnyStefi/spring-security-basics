package org.security.basics.entity

import jakarta.persistence.*

@Entity
@Table(name = "happy_authorities")
data class HappyAuthorityEntity(
    @Id
    @Column(nullable = false, unique = true)
    val authority: String
) {
    constructor() : this("")
}