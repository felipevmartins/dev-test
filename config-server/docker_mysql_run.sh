docker run -d --name MySQLServer -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=userapidb -e MYSQL_USER=userapi -e MYSQL_PASSWORD=userapi123 -p 3336:3336 mysql:5.7
