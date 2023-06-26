default:
	echo "Make Rules: starbucks-network, starbucks-api-run, cashier-nodejs-run, starbucks-app-run"

starbucks-network:
	docker network create --driver bridge starbucks

starbucks-api-run: 
	docker run --network starbucks --name spring-starbucks-api -td -p 8080:8080 paulnguyen/spring-starbucks-api:v3.1	

cashier-nodejs-run:
	docker run --network starbucks --name starbucks-nodejs -p 90:9090  \
	-e "api_endpoint=http://spring-starbucks-api:8080" -td paulnguyen/starbucks-nodejs:v3.0

starbucks-app-run: 
	java -cp starbucks-app.jar \
		-Dapiurl="http://34.30.230.73/api" \
		-Dapikey="Zkfokey2311" \
		-Dregister="1393478" \
		starbucks.Main 2>debug.log

# Kong Config
compose-up2:
	docker-compose up --scale starbucks=2 -d


kong-config:
	http :8001/config config=@kong.yaml

kong-reload:
	docker exec -it $(id) kong reload

# Compose

network-ls:
	docker network ls

network-create:
	docker network create --driver bridge $(network)

network-prune:
	docker network prune



compose-up-1:
	docker-compose up --scale starbucks=1 -d

client-up:
	docker-compose up -d starbucks-client

kong-up:
	docker-compose up -d kong	

starbucks-up:
	docker-compose up -d starbucks

lb-up:
	docker-compose up -d starbucks-service

jumpbox-up:
	docker-compose up -d jumpbox

mysql-up:
	docker-compose up -d mysql

compose-down:
	docker-compose down

lb-stats:
	echo "user = admin | password = admin"
	open http://localhost:1936

lb-test:
	open http://localhost


kongLab8:
	docker run -d --name kong \
    --network=kong-network \
    -e "KONG_DATABASE=off" \
    -e "KONG_PROXY_ACCESS_LOG=/dev/stdout" \
    -e "KONG_ADMIN_ACCESS_LOG=/dev/stdout" \
    -e "KONG_PROXY_ERROR_LOG=/dev/stderr" \
    -e "KONG_ADMIN_ERROR_LOG=/dev/stderr" \
    -e "KONG_ADMIN_LISTEN=0.0.0.0:8001, 0.0.0.0:8444 ssl" \
    -p 80:8000 \
    -p 443:8443 \
    -p 8001:8001 \
    -p 8444:8444 \
    kong:2.4.0

kongpt2:
	docker exec -it kong kong config init /home/kong/kong.yaml


starbucks-app-run1:
	java -cp starbucks-app.jar \
		-Dapiurl="http://34.30.230.73/api" \
		-Dapikey="Zkfokey2311" \
		-Dregister="5012349" \
		starbucks.Main 2>debug.log