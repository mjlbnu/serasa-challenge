# Desafio Serasa

## Tecnologias utilizadas

* Maven
* Springboot
* Spring-JPA
* Spring-WEB
* Banco de dados H2
* Flyway
* OpenCSV
* Thymeleaf
* Bootstrap

### Build do projeto utilizando Maven:
Rodar o comando "mvn install" na pasta raiz do projeto;
Será criado o arquivo "desafioSerasa-1.0.0-SNAPSHOT.jar" na pasta target.

### Rodar o projeto localmente após o build:
Executar o comando "$ java -jar desafioSerasa-1.0.0-SNAPSHOT.jar" na pasta "target" que contém o .jar

### Heroku
Foi realizado o deploy do projeto no Heroku, **para acessar**, clique [aqui](https://ser-challenge.herokuapp.com/).

> É  [política da plataforma](https://devcenter.heroku.com/articles/dynos#dyno-sleeping) manter os aplicativos em estado de "dormência" após uma hora de inatividade. Isso causa um pequeno atraso na primeira solicitação, mas as solicitações subsequentes serão executadas normalmente