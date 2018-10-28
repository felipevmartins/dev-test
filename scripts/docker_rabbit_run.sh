runcommand="docker run -d --name RabbitMQServer -p 5672:5672 -p 15672:15672 rabbitmq:3.7.8-management"
if [ ! "$($runcommand)" ]; then
  docker container start RabbitMQServer
fi
