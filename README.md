# 🛠️ MaintenerAPI
A **MaintenerAPI** é uma aplicação RESTful desenvolvida com **Spring Framework**, voltada para o gerenciamento de registros de manutenção, status, funcionários, ferramentas e suas associações. 

## 📌 Visão Geral
A MaintenerAPI permite realizar operações de CRUD (Create, Read, Update, Delete) sobre os seguintes recursos:

- Manutenções
- Status de Manutenção
- Funcionários
- Ferramentas
- Associação de Ferramentas com Manutenções

## ⚙️ Configuração do Ambiente

### Configuração do Banco de Dados Oracle
O banco de dados utilizado está disponível na pasta [database](https://github.com/RICKBISPO/MaintenerAPI/tree/main/database) do repositório principal do projeto.
Para conectar a aplicação ao banco de dados Oracle, configure as propriedades abaixo no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XE
spring.datasource.username=[username]
spring.datasource.password=[password]
```


