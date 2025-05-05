generate-cert:
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


demo:
	curl -vu user:12345  https://localhost:8080/hello