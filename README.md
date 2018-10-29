# dev-test
## Spring MVC, Cloud, Security and RabbitMQ
#### Projeto criado para fins de avaliação e nivelamento de conhecimento sobre as tecnologias acima citadas.

O objetivo desse teste é avaliar uma solução Java para a Nuvem baseada em tecnologias Spring
Boot/Spring Cloud.
Deverá ser implementado uma aplicação WEB com REST contendo um endpoint de login. A autenticação
deve ser realizada com Spring Security e a sessão do usuário autenticado deverá ser mantida em uma
estrutura de cache externa (Redis ou GemFire). A sessão não poderá estar contida no contexto da
aplicação.
Deverá conter também um API de usuários com todas as operações básicas de um CRUD e o usuário
deverá ter o seguinte modelo:

* Id
* name: <obrigatório>
* login: <obrigatório>
* password: <obrigatório>
* createdDate: <obrigatório>
* updatedDate
* e-mail : <obrigatório>
* admin (Flag): <obrigatório>

>Obs.: Somente usuário admin pode atualizar senhas de outros usuários.

Deve ser implementando também um endpoint “/email” que receberá uma mensagem de email a ser
direcionada para o RabbitMQ.
Deverá ser implementado uma outra aplicação que tratará mensagens assíncronas do RabbitMQ,
enviadas previamente pela aplicação WEB. O consumo da fila de mensagens enviará os emails para os
seguintes casos:
  - Todos os usuários Administradores;
  - Usuário específico que será recebido com parâmetro na requisição.

As aplicações devem consumir dados relacionados a configurações de serviços como banco de dados e
mensageria, de um servidor de configuração.
A definição de componentes como Templates de mensageria, Connection factories, Data Source, Entity
Manager devem ser configuradas utilizando Java Config Class (evitar de configurações default em
properties e configurações em xmls).

#### Foram criadas 4 aplicações para atenter o objetivo:

* web-application:
  * Projeto Web criado com Spring Boot
  * Utiliza o Spring Security e consome dados da user-api para login de usuários (apenas endpoint, sem interface).
  * Sessão salva no banco de dados Redis.
  * Endpoint para envio de emails à fila do RabbitMQ.

* user-api:
  * Api de CRUD para usuários do sistema.
  * Persistência em banco de dados MySQL

* email-consumer:
  * Aplicação que consome a fila de emails enviados ao RabbitMQ.

* config-server:
  * Spring Cloud Config Server onde foram centralizadas todas as informações referentes à configuração das aplicações.

## Configuração do Projeto
### Necessário que a máquina possua instalados:
- java 8
- maven
- docker
- docker-compose

### Clonar projeto:
```sh
git clone https://github.com/thiagobcar/dev-test.git
```

### Preparar arquivos de configuração:
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
spring.cloud.config.server.git.uri=file:///***config_path***/config
```

## Execução

### Pela IDE
- Importar projetos pela IDE
- Alterar arquivo "docker-compose.yml" deixando da seguinte forma:
```yml
version: '3.1'

services:

  mysql:
    image: mysql:5.7
    environment:
      - MYSQL_ROOT_PASSWORD=root123
      - MYSQL_DATABASE=userapidb
      - MYSQL_USER=userapi
      - MYSQL_PASSWORD=userapi123
    ports:
      - 3336:3306

  rabbitmq:
    image: rabbitmq:3.7.8-management
    ports:
      - 5672:5672
      - 15672:15672
  
  redis:
    image: redis:5.0.0
    ports:
      - 6379:6379
```
> Contendo apenas os containers dos quais a aplicação depende.
- Executar diretamente pela IDE as aplicações na seguinte ordem:
  * config-server
  * user-api
  * web-aplication
  * email-consumer

### Pela Linha de Comando
#### Executar build:
- No script build.sh são gerados os .jars dos projetos e criados os containers de cada um.
> Informar senha caso necessário.
```sh
chmod +x build.sh
./build.sh
```

#### Executar run:
- No script run.sh o docker-compose é executado para iniciar os containers necessários às aplicações.
- Também é criado um usuário inicial *login*:**admin** *password*:**admin**.
> nem todas as aplicações estão com seus containers funcionais, por isso foram comentadas no arquivo docker-compose.yml e executadas diretamente com java -jar.

> Informar senha caso necessário.
```sh
chmod +x run.sh
./run.sh
```
> Execução pode ser acompanhada pelo arquivo "log.txt"
```sh
tail -f log.txt
```
#### As aplicações ficarão disponíveis em:
* Web Application: http://localhost:8085
* User Api: http://localhost:8086

## Documentação Rest utilizando o Swagger
### O swagger foi incluído nos projetos web e user-api para realizar uma documentação automática da api Rest.
* Web Application: http://localhost:8085/swagger-ui.html
* User Api: http://localhost:8086/swagger-ui.html

### Testes com Postman
* Foi incluído um projeto simples do Postman que utilizei para realizar testes.
  * "dev-test.postman_collection.json"
