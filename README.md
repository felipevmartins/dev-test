# dev-test
Projeto Spring usando MVC, Cloud, Security and RabbitMQ

### Configuração
#### Necessário que a máquina possua instalados:
- java 8
- maven
- docker
- docker-compose

#### Clonar projeto:

```sh
git clone
```

#### Preparar arquivos de configuração:
- Fazer unzip do arquivo config.zip
```sh
unzip config.zip
```
- Iniciar repositório local com git
```sh
cd config
git init
git add .
git commit -m "Config"
```
- Ainda no diretório config, nos arquivos "emailconsumer.properties" e "webapplication.properties", alterar o ip 
"192.168.1.8" para o ip local da sua máquina na rede (por algum motivo o container do RabbitMQ não ficou disponível
no ip padrão).
```
...
rabbitmq.host=***ip da sua maquina na rede***
rabbitmq.port=5672
...
```
- No arquivo "application.properties", do projeto config-server, alterar o caminho até o diretório config:
```
server.port=8001
spring.application.name=configserver
spring.cloud.config.server.git.uri=file:///***user_path***/config
```
