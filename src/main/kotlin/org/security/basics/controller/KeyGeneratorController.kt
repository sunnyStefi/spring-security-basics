package org.security.basics.controller

import org.springframework.security.crypto.keygen.BytesKeyGenerator
import org.springframework.security.crypto.keygen.KeyGenerators
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/keys")
class KeyGeneratorController {

    private val sharedGenerator: BytesKeyGenerator = KeyGenerators.shared(32)

    @GetMapping("/string")
    fun getStringKey(): Map<String, String> {
        val key : String = KeyGenerators.string().generateKey()
        return mapOf("type" to "string", "key" to key)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @GetMapping("/bytes")
    fun getByteKey(): Map<String, String> {
        val key : ByteArray = KeyGenerators.secureRandom().generateKey()
        return mapOf(
            "type" to "bytes",
            "key" to key.toHexString(), // equivalent to key.joinToString("") { "%02x".format(it) }
            "length" to "${key.size} bytes"
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    @GetMapping("/shared")
    fun getSharedKeys(): Map<String, Any> {
        val key = sharedGenerator.generateKey()
        return mapOf(
            "type" to "shared",
            "key" to key.toHexString()
        )
    }
}
