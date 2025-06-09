package org.security.basics.utilities

import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class SscmTest {

    @Autowired
    lateinit var sscm: Sscm

    @Test
    fun `should generate two different salts`() {
        val (saltString, saltBytes) = sscm.generateSaltsFromStringAndBytes()
        println("salt1 = $saltString, saltBytes = $saltBytes")
        assertThat(saltString).isNotEqualTo(saltBytes)
    }

    @Test
    fun `should generate the same key with shared`(){
        val (saltBytes1, saltBytes2) = sscm.generateSharedKeys()
        println("salt1 = $saltBytes1, saltBytes = $saltBytes2")
        assertThat(saltBytes1).isEqualTo(saltBytes2)
    }

}