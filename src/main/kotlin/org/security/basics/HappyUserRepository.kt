package org.security.basics

import org.springframework.data.jpa.repository.JpaRepository

interface HappyUserRepository : JpaRepository<HappyUserEntity, Long> {
    fun findByUsername(username: String): HappyUserEntity?
}