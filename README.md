# 🔐 Spring Security Basics | UserDetails & UserDetailsService Implementation

Welcome to the **Spring Security Basics** series!
In this branch, we build a simple app that saves users in an H2 in-memory database and supports login with those users using a custom `UserDetailsService`.

## 🎥 Youtube Video Tutorial

[Basic 04 | UserDetailsService with H2 Database]() by [Spring Security with Stefania](https://www.youtube.com/channel/UCD7izGaUlRDhJaOa5Y4Cc7Q?sub_confirmation=1) 🔔

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/05-user-details
```

## 🧩 Key concepts

In this module (`basics/05-user-details`), you will learn how to:

* Save users in an H2 in-memory database using JPA
* Implement a `UserDetailsService` that loads users from our H2 database
* Configure Spring Security to authenticate users against a real DB
* Redirect users after login

## 🏗️ Code Overview

### 🧪

Dependencies:
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
        
<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <scope>runtime</scope>
</dependency>
```

Properties:
```yml
spring:
  datasource:
    url: jdbc:h2:mem:spring-basics
    driver-class-name: org.h2.Driver
    username: admin
    password: admin
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

```
Load users from DB:
```kotlin
@Bean
fun loadUserByUsername(happyUserRepository: HappyUserRepository): UserDetailsService {
    return UserDetailsService { username ->
        val userEntity = happyUserRepository.findByUsername(username)

        if (userEntity != null) {
            HappyUser(userEntity)
        } else {
            throw UsernameNotFoundException("User not found: $username")
        }
    }
}
```
Redirect after login:
```kotlin
http
  .formLogin { it.defaultSuccessUrl("/hello", true) }
  .httpBasic()
```

## 🚀 Next Steps
In the next branch, we will persist users in a real database and enhance authentication flows with password encoding and validation. 
Thank you for joining me in this amazing journey and happy coding! 🚀
