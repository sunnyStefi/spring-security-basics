# 🔐 Spring Security Basics 04 | `Authentication Provider`

Welcome to the **Spring Security Basics** series!
In this branch, we’ll override some of the Spring Security default configurations to better understand and control the authentication process.

## 🎥 Youtube Video Tutorial
in progress...

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/04-authentication-provider
```

## 🧩 Key concepts

In this module (`basics/04-authentication-provider` branch), we focus on **customizing the `AuthenticationProvider`** interface. 



## 🏗️ Code Overview

### 🧪 Create users with `CustomAuthenticationProvider`

```kotlin
@Bean
override fun authenticate(authentication: Authentication): Authentication {
  if ("user" == authentication.name && "12345" == authentication.credentials) {
    return UsernamePasswordAuthenticationToken(username, password, null)
  } else throw AuthenticationCredentialsNotFoundException("")
}

override fun supports(authentication: Class<*>): Boolean {
  return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}
}
```


## ⚠️ Best Practices

## 🚀 Next Steps

In the next branch, we’ll continue building up our authentication configuration and learn how to read our users from a database and login with those credentials 🤯

Stay tuned!

---
