# 🔐 Spring Security Basics | AuthenticationProvider Implementation

Welcome to the **Spring Security Basics** series!
In this branch, we explore how to build and register a custom `AuthenticationProvider` in Spring Security.

## 🎥 Youtube Video Tutorial

[Basic 04 | Custom AuthenticationProvider](https://www.youtube.com/channel/UCD7izGaUlRDhJaOa5Y4Cc7Q?sub_confirmation=1) by [Spring Security with Stefania](https://www.youtube.com/channel/UCD7izGaUlRDhJaOa5Y4Cc7Q?sub_confirmation=1) 🔔

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/04-authentication-provider
```

## 🧩 Key concepts

In this module (`basics/04-authentication-provider` branch), we cover how `AuthenticationProvider` encapsulates authentication logic and registering it in Spring Security’s filter chain

## 🏗️ Code Overview

### Custom AuthenticationProvider (Kotlin)

```kotlin
class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        val userDetails = userDetailsService.loadUserByUsername(username)
        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw AuthenticationCredentialsNotFoundException("Invalid credentials")
        }

        return UsernamePasswordAuthenticationToken(
            userDetails, password, userDetails.authorities
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
```

### Registering in Security Configuration

```kotlin
@Bean
fun securityFilterChain(http: HttpSecurity, customAuthProvider: CustomAuthenticationProvider): SecurityFilterChain {
    http
        .authenticationProvider(customAuthProvider)
    return http.build()
}
```

## 🚀 Next Steps

In the next branch we will connect authentication to a real in-memory H2 database 🤯, persisting users with JPA and managing them with a custom `UserDetailsService`.
