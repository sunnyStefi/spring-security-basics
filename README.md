# spring-security-basics 
## https request with default user-information

### Spring Security Authentication Flow
<Add schema>

Every request passes through an Authentication Filter.
The Authentication Provider implements the authentication logic 
delegating to UserDetailsService, which finds the user (GET) 
and to PasswordEncoder, which encodes and verify the password.

The Authentication Filter saves the final authenticated data
in the Security context.

## 1. UserDetailsService
Autoconfigured bean. Retrieves the User (GET). 
Default implementation: default credentials are stored in memory when
app is loaded.

## 2. PasswordEncoder
Autoconfigured bean. Encodes and verifies the password with an existing encoding.
Mandatory. Simplest implementation is no encoding.
Lives together with UserDetailsService.

### Enable HTTPS
Generate a self-signed certificate with Open SSL
Transform it to pk12
<makefile reference>
Enable https at the application level in the properties file
Add it to your keystore (mac) and trust
```bash
make demo
```

### Resources
[L. Spilca, Spring Security in Action, 2nd Edition]()
Chapter 2.2, Hello Spring Security 