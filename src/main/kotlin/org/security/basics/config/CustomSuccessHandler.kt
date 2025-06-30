package org.security.basics.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class CustomSuccessHandler : AuthenticationSuccessHandler{

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication ) {
        if (authentication.name.contains("user")) {
            return response.sendRedirect("/beach")
        }
        return response.sendRedirect("/login")

    }
}