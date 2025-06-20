# 🔐 Spring Security Basics | Passwords and Cryptography

Welcome to the **Spring Security Basics** series!
In this branch, we explore how to securely store and verify passwords using different hashing algorithms.

## 🎥 Youtube Video Tutorial

[Basic 06 | PasswordEncoder](https://youtu.be/jae3-5QXyEA) by [Spring Security with Stefania](https://www.youtube.com/channel/UCD7izGaUlRDhJaOa5Y4Cc7Q?sub_confirmation=1) 🔔

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/07-password-encoder
```

## 🧩 Key concepts

In this module (`basics/07-password-encoder`), we explore password hashing, salting, adaptive algorithms, and Spring Security's `PasswordEncoder` options like BCrypt, SCrypt, and Argon2.

## 🏗️ Code Overview

### 🧪 PasswordEncoder Configuration

```kotlin
@Bean
fun encoder(): PasswordEncoder {
   val encoders: MutableMap<String, PasswordEncoder> = mutableMapOf()
   encoders["classic_encoder"] = BCryptPasswordEncoder(11)
   encoders["flavored_encoder"] = SCryptPasswordEncoder(65536, 8, 1, 32, 64)
   val delegating = DelegatingPasswordEncoder("flavored_encoder", encoders)
   return TimingPasswordEncoder(delegating)
}
```

## 🔍 Summary

* Hashing is one-way: the original password can't be retrieved.
* SHA-256 is outdated —use adaptive hashing like BCrypt, SCrypt, or Argon2.
* BCrypt (default) is slow and secure: use strength factor to tune
* SCrypt and Argon2 offer better memory and CPU resistance
* DelegatingPasswordEncoder supports multiple formats (e.g. `{bcrypt}`, `{scrypt}`)
* You can decorate encoders (e.g. `TimingPasswordEncoder`) to measure performance
* OWASP recommends password verification to take \~1 second ⏰
