# 🔐 Spring Security Basics | JDBC UserDetailsManager

Welcome to the **Spring Security Basics** series!
In this branch, we use a real database (MySQL) to store and retrieve users via `JdbcUserDetailsManager`.

## 🎥 Youtube Video Tutorial

[Basic 06 | JdbcUserDetailsManager & MySQL Setup](https://youtu.be/mV23gaY6s2M) by [Spring Security with Stefania](https://www.youtube.com/channel/UCD7izGaUlRDhJaOa5Y4Cc7Q?sub_confirmation=1) 🔔

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/06-user-details-manager
```

You’ll need MySQL running locally and the following dependency:

```xml
<!-- pom.xml -->
<dependency>
   <groupId>com.mysql</groupId>
   <artifactId>mysql-connector-j</artifactId>
   <scope>runtime</scope>
</dependency>
```

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/basics?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
```

## 🧩 Key concepts

In this module we'll (`basics/06-user-details-manager`):

* Replace in-memory users with real users from MySQL
* Use `JdbcUserDetailsManager` to manage users
* Customize queries for non-default table names

## 🏗️ Code Overview

### 🧪 loadUserByUsername setup

```kotlin
@Bean
fun loadUserByUsername(dataSource: DataSource): UserDetailsService {
   val manager = JdbcUserDetailsManager(dataSource)
   manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM happy_users WHERE username = ?")
   manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM happy_user_authorities WHERE username = ?")
   return manager
}
```

### 🗄️ User - Authority relationship

```kotlin
@Entity
@Table(name = "happy_users")
class HappyUserEntity(
   @Id
   @Column(nullable = false, unique = true)
   val username: String,
   var password: String = "",
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
       name = "happy_user_authorities",
       joinColumns = [JoinColumn(name = "username")],
       inverseJoinColumns = [JoinColumn(name = "authority")]
   )
   var authorities: MutableSet<HappyAuthorityEntity> = mutableSetOf(),
   var enabled: Boolean = true,
)
```

### 🔐 Custom PasswordEncoder

```kotlin
@Bean
fun encoder() = object : PasswordEncoder {
   override fun encode(password: CharSequence) = password.toString()
   override fun matches(password: CharSequence, encodedPassword: String) = password == encodedPassword
}
```
