sudo docker-compose up --remove-orphans &>> log.txt &
sleep 40
java -jar ./user-api/target/user-api-1.0.0.jar &>> log.txt &
sleep 20
java -jar ./web-application/target/web-application-1.0.0.jar &>> log.txt &
sleep 20
java -jar ./email-consumer/target/email-consumer-1.0.0.jar &>> log.txt &