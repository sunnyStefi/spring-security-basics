package org.security.basics.utilities

import org.springframework.security.crypto.keygen.KeyGenerators
import org.springframework.stereotype.Service

@Service
class Sscm {

    fun generateSaltsFromStringAndBytes(): Pair<String,String> {
        val key1 = KeyGenerators.string().generateKey()
        val key2Bytes = KeyGenerators.secureRandom().generateKey()
        val key2 = String(key2Bytes, Charsets.UTF_8)
        return Pair(key1, key2)
    }

    fun generateSharedKeys(): Pair<String,String> {
        val keyGenerator = KeyGenerators.shared(16)
        val key1 = String(keyGenerator.generateKey(), Charsets.UTF_8)
        val key2 = String(keyGenerator.generateKey(), Charsets.UTF_8)
        return Pair(key1, key2)
    }

}