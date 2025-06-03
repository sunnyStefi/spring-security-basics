package org.security.basics.entity

import jakarta.persistence.*

@Entity
@Table(name = "happy_users")
class HappyUserEntity(

    @Id
    @Column(nullable = false, unique = true)
    val username: String,

    var password: String = "",

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "happy_user_authorities",
        joinColumns = [JoinColumn(name = "username", referencedColumnName = "username")],
        inverseJoinColumns = [JoinColumn(name = "authority", referencedColumnName = "authority")]
    )
    var authorities: MutableSet<HappyAuthorityEntity> = mutableSetOf(),

    var enabled: Boolean = true,
){
    constructor() : this("")
}