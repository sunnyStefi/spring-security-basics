# 🔐 Spring Security Basics 03 | `UserDetailsService`

Welcome to the **Spring Security Basics** series! 
In this branch, we’ll override some of the Spring Security default configurations to better understand and control the authentication process.

## 🎥 Watch the Video Tutorial [Basic 03 | HTTPS, UserDetailsService]()

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/03-user-details-service
```

## 🧩 Key concepts

In this module (`basics/03-user-details-service` branch), we focus on **customizing the `UserDetailsService`**. This service is essential in Spring Security for retrieving user data during authentication.

* `UserDetailsService`: Interface used to retrieve user information
* `InMemoryUserDetailsManager`: A simple implementation that stores users in memory
* `User`: A Spring-provided implementation of `UserDetails`
* `loadUserByUsername`: The core method used to fetch user details at login* Two customization styles:

* Two customization styles:
  * **Style 1**: Defining `UserDetailsService` as a Bean
  * **Style 2**: Through `SecurityFilterChain`


## 🏗️ Code Overview

### 🧪 Create users with `InMemoryUserDetailsManager`

```kotlin
@Bean
fun userDetails(): UserDetailsService {

    val admin = User.withUsername("admin")
        .password("{noop}admin")
        .roles("ADMIN")
        .build()
  
        ...more users here...
  
    return InMemoryUserDetailsManager(admin, ...)
}
```

* `{noop}` tells Spring not to encode the password (use only for testing!)
* The `User` class is an implementation of `UserDetails`
* Users are stored in a `Map` internally for quick lookup


## ⚠️ Best Practices

* Stick to **one customization style** per project/module for clarity and maintainability
* Avoid `{noop}` in production environments — use a proper `PasswordEncoder`



## 🚀 Next Steps

In the next branch, we’ll continue building up our authentication configuration and learn how to customize an **AuthenticationProvider** 🤯

Stay tuned!

---
