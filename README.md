# 🔐 Spring Security Basics 02 | HTTPS

Welcome to the **Spring Security Basics** series!
In this module (`basics/02-https-curl` branch) we'll explore HTTPs, openssl by installing a self-signed certificate, and using curl to test a https request.

## 🎥 Watch the Video Tutorial [Basic 02 | HTTPS]()

## 🛠️ Setup

This content lives in a separate branch for modular learning:

```bash
git checkout basics/02-https-curl
```

## 🏗️ Code Overview

### 🧪 Enable HTTPS in Spring Boot

```yaml
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=yourPassword
server.ssl.key-store-type=PKCS12
```

### 🧪 create a p12 self-signed certificate

```bash
	cd src/main/resources && \
    printf "[dn]\nCN=localhost\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:localhost\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth\n" > cert.conf && \
    openssl req -x509 -newkey rsa:2048 -nodes -sha256 \
    -keyout localhost.key -out localhost.crt \
    -subj "/CN=localhost" -extensions EXT -config cert.conf && \
    openssl pkcs12 -export \
    -in localhost.crt -inkey localhost.key \
    -out localhost.p12 -name localhost \
    -certfile localhost.crt -passout pass:123 && \
    rm cert.conf
```

## ⚠️ Best Practices`

✅ Robust HTTPS enforcement = Infrastructure + Application
✅ Always test security headers and TLS
✅ Use real CA-signed certs for production, self-signed only for dev

## 🚀 Next Steps

In the next branch you are going to customize the *UserDetailsService* 😌. 
Keep learning and happy coding!

