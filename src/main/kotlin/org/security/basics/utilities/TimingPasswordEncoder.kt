package org.security.basics.utilities

import org.springframework.security.crypto.password.PasswordEncoder

class TimingPasswordEncoder(private val delegate: PasswordEncoder) : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        return delegate.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        val start = System.nanoTime()
        val result = delegate.matches(rawPassword, encodedPassword)
        val durationMs = (System.nanoTime() - start) /1_000_000
        println("Password matches check took $durationMs ms")
        return result
    }

    override fun upgradeEncoding(encodedPassword: String): Boolean {
        return delegate.upgradeEncoding(encodedPassword)
    }
}
