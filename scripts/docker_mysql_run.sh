runcommand="docker run -d --name MySQLServer -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=userapidb -e MYSQL_USER=userapi -e MYSQL_PASSWORD=userapi123 -p 3336:3306 mysql:5.7"
if [ ! "$($runcommand)" ]; then
  docker container start MySQLServer
fi
