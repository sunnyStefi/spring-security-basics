host-cup-memory:
	sysctl -n hw.logicalcpu
	sysctl -n hw.memsize
	sysctl -a

# cpu: quota/period = time
# memory: bytes/1024*1024 = MB
app-cpu-memory:
	cat /sys/fs/cgroup/memory.max
	cat /sys/fs/cgroup/cpu.max

create-network:
	docker network create appnet

run-mysql:
	docker run -d \
      --name db \
      --network appnet \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=basics \
      -p 3306:3306 \
      mysql:8.0

build-app:
	docker build -t basics-app .

#1 run the app with default --cpus=2
run-app-m:
	docker run -it \
      --name app-m \
      --network appnet \
      -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/basics \
      -e SPRING_DATASOURCE_USERNAME=root \
      -e SPRING_DATASOURCE_PASSWORD=root \
      basics-app

# adjust the hashing algorithm: it should not kill the application!
run-app-xs:
	docker run -it \
      --name app-xs \
      --network appnet \
      --memory="2G" \
      --cpus=1 \
      -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/basics \
      -e SPRING_DATASOURCE_USERNAME=root \
      -e SPRING_DATASOURCE_PASSWORD=root \
      basics-app

