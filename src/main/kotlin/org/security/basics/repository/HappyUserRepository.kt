package org.security.basics.repository

import org.security.basics.entity.HappyUserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface HappyUserRepository : JpaRepository<HappyUserEntity, Long> {
    fun findByUsername(username: String): HappyUserEntity?
}