runcommand="docker run --name RedisServer -d redis:5.0.0"
if [ ! "$($runcommand)" ]; then
  docker container start RedisServer
fi
