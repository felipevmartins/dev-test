echo "Starting project..."
echo " - Starting docker containers..."
sudo docker-compose up --remove-orphans &>> log.txt &
sleep 20
echo " - Docker containers started!"
echo " - Starting config server..."
java -jar ./config-server/target/config-server-1.0.0.jar &>> log.txt &
sleep 20
echo " - Config server started!"
echo " - Starting user api..."
java -jar ./user-api/target/user-api-1.0.0.jar &>> log.txt &
sleep 30
echo " - User api started!"
echo " - Creating admin user"
curl -X POST "http://localhost:8086/user/insert" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"admin\": true, \"createdDate\": \"2018-10-28T17:26:03.451Z\", \"email\": \"admin@email.com\", \"id\": 0, \"login\": \"admin\", \"name\": \"admin\", \"password\": \"admin\", \"updatedDate\": \"2018-10-28T17:26:03.451Z\"}"
echo " "
echo " - Starting web application..."
java -jar ./web-application/target/web-application-1.0.0.jar &>> log.txt &
sleep 30
echo " - Web application started!"
echo " - Starting email consumer..."
java -jar ./email-consumer/target/email-consumer-1.0.0.jar &>> log.txt &
echo " - Email consumer started!"
echo "Started successfully!"