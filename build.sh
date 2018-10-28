cd common-dto
mvn install -DskipTests
cd ..

cd config-server
mvn install -DskipTests
sudo docker build . --build-arg JAR_FILE=config-server-1.0.0.jar -t config-server
cd ..

cd web-application
mvn install -DskipTests
sudo docker build . --build-arg JAR_FILE=web-application-1.0.0.jar -t web-application
cd ..

cd email-consumer
mvn install -DskipTests
sudo docker build . --build-arg JAR_FILE=email-consumer-1.0.0.jar -t email-consumer
cd ..

cd user-api
mvn install -DskipTests
sudo docker build . --build-arg JAR_FILE=user-api-1.0.0.jar -t user-api
