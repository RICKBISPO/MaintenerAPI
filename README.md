# 🛠️ MaintenerAPI
A **MaintenerAPI** é uma aplicação RESTful desenvolvida com **Spring Boot**, voltada para o gerenciamento de registros de manutenção, status, funcionários, ferramentas e suas associações. 
Este projeto tem como objetivo explorar diferentes formas de implementação de endpoints, não seguindo, portanto, um padrão de projeto unificado.

## 📌 Visão Geral
A MaintenerAPI permite realizar operações de CRUD (Create, Read, Update, Delete) sobre os seguintes recursos:

- Manutenções
- Status de Manutenção
- Funcionários
- Ferramentas
- Associação de Ferramentas com Manutenções

## ⚙️ Configuração do Ambiente

### 2. Configuração do Banco de Dados Oracle
O banco de dados utilizado está disponível na pasta [database](https://github.com/RICKBISPO/MaintenerAPI/tree/main/database) do repositório principal do projeto.
Para conectar a aplicação ao banco de dados Oracle, configure as propriedades abaixo no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XE
spring.datasource.username=[username]
spring.datasource.password=[password]
```

## 📡 Acessando a API

A API estará disponível em:

```
http://localhost:8080/api
```

A documentação interativa (Swagger UI) estará disponível em:

```
http://localhost:8080/swagger-ui.html
```

## 🧪 Testando a API

Você pode utilizar ferramentas como:

- [Postman](https://www.postman.com/)
- [Insomnia](https://insomnia.rest/)
- Ou diretamente pelo Swagger UI

## 👨‍💻 Contribuição

Sinta-se à vontade para contribuir com sugestões, melhorias e correções. Basta fazer um fork do projeto e abrir um Pull Request. 😉

