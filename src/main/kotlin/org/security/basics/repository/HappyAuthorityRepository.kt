package org.security.basics.repository

import org.security.basics.entity.HappyAuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface HappyAuthorityRepository : JpaRepository<HappyAuthorityEntity, Long> {
    fun findByAuthority(username: String): HappyAuthorityEntity?
}