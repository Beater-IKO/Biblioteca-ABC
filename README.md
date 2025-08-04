# Biblioteca-ABC


Projeto de back-end desenvolvido em **Spring Boot** para gerenciar uma biblioteca fictícia.  
O sistema implementa CRUD completo (Create, Read, Update, Delete) para as entidades **Biblioteca**, **Livro**, **Autor** e **Editora**.

Foi utilizado java 17, Spring Boot 3, Maven e Postman(para testes)


Estrutura:
src/main/java/br/com/bliblioteca_abc/biblioteca
│
├── BibliotecaApplication.java # Classe principal do Spring Boot
│
├── controller # Controllers (endpoints REST)
│ ├── BibliotecaController.java
│ ├── LivroController.java
│ ├── AutorController.java
│ └── EditoraController.java
│
├── model # Modelos (entidades)
│ ├── Biblioteca.java
│ ├── Livro.java
│ ├── Autor.java
│ └── Editora.java
│
├── repository # Repositórios (vazio por enquanto)
│
└── services # Camada de serviços
├── BibliotecaService.java
├── LivroService.java
├── AutorService.java
└── EditoraService.java


Como executar o projeto:
1º Clone o repositório
2º Entre na pasta do projeto: cd biblioteca-abc
3º Execute o projeto com o Maeven: mvn spring-boot:run
4º O servidor estará assim: http://localhost:8080


EndPoints disponíveis:

**Biblioteca**
GET /bibliotecas → lista todas as bibliotecas

GET /bibliotecas/{id} → busca uma biblioteca por ID

POST /bibliotecas → cria uma nova biblioteca

PUT /bibliotecas/{id} → atualiza uma biblioteca existente

DELETE /bibliotecas/{id} → deleta uma biblioteca

**Livro**
GET /livros

GET /livros/{id}

POST /livros

PUT /livros/{id}

DELETE /livros/{id}

**Autor**
GET /autores

GET /autores/{id}

POST /autores

PUT /autores/{id}

DELETE /autores/{id}

**Editora**
GET /editoras

GET /editoras/{id}

POST /editoras

PUT /editoras/{id}

DELETE /editoras/{id}


Testando com o Postman:

Abra o Postman
Crie uma nova requisição
Configure:
URL: http://localhost:8080/bibliotecas (ou livros, autores, editoras)
Método HTTP: GET, POST, PUT ou DELETE
Para POST e PUT, use Body → raw → JSON e cole o exemplo do endpoint


Observações:

O projeto não utiliza banco de dados. Os dados ficam em memória enquanto a aplicação está em execução.
Não foram implementados relacionamentos entre entidades (cada CRUD é independente).
Ideal para aprendizado de REST APIs com Spring Boot.
