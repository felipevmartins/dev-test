# dev-test
## Spring MVC, Cloud, Security and RabbitMQ
#### Projeto criado para fins de avaliação e nivelamento de conhecimento sobre as tecnologias acima citadas.
#### Foram criadas 4 aplicações com as seguintes características:

* web-application:
  * Projeto Web criado com Spring Boot
  * Utiliza o Spring Security e consome dados da user-api para login de usuários (apenas endpoint, sem interface).
  * Cache de sessão salvo no banco de dados Redis.
  * Endpoint para envio de emails à fila do RabbitMQ.

* user-api:
  * Api de CRUD para usuários do sistema.
  * Persistência em banco de dados MySQL

* email-consumer:
  * Aplicação que consome a fila de emails enviados ao RabbitMQ.

* config-server:
  * Spring Cloud Config Server onde foram centralizadas todas as informações referentes à configuração das aplicações.

## Configuração
#### Necessário que a máquina possua instalados:
- java 8
- maven
- docker
- docker-compose

#### Clonar projeto:
```sh
git clone https://github.com/thiagobcar/dev-test.git
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

#### Executar build:
- No script build.sh são gerados os .jars dos projetos e criados os containers de cada um.
> Informar senha caso necessário.
```sh
chmod +x build.sh
./build.sh
```

#### Executar run:
- No script run.sh chamamos o docker-compose para iniciar os containers necessários às nossas aplicaçes.
- Também é criado um usuário inicial *login*:**admin** *password*:**admin**.
> nem todas as aplicações estão com seus containers funcionais, por isso foram comentadas no arquivo docker-compose.yml e executadas diretamente com java -jar.

> Informar senha caso necessário.
```sh
chmod +x run.sh
./run.sh
```

## Documentação Rest com Swagger
#### O swagger foi incluído nos projetos web e user-api para realizar uma documentação automática da api Rest.
* Web Application: http://localhost:8085/swagger-ui.html
* User Api: http://localhost:8086/swagger-ui.html
