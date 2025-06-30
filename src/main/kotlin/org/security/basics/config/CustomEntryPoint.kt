package org.security.basics.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.web.AuthenticationEntryPoint

class CustomEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: org.springframework.security.core.AuthenticationException?
    ) { response?.setHeader("Failed login", "Oops")
       response?.sendError(HttpStatus.UNAUTHORIZED.value())
    }
}