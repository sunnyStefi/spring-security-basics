demo-400:
	 curl -v http://localhost:8080/hello

demo-before:
	 curl -vu user:fd998b0b-abeb-4915-ba96-69dfef9ee4f1 -H "IsHappyRequest: yes Im happy" http://localhost:8080/hello

demo-at:
	 curl -vH "IsHappyRequestId:123" http://localhost:8080/hello
