# Helpdesk API

Este é um projeto de API para um sistema de Helpdesk, desenvolvido utilizando o Spring Boot. A API fornece operações CRUD para gerenciar usuários e outras entidades relacionadas ao sistema de suporte técnico. O projeto está contêinerizado usando Docker e utiliza PostgreSQL como banco de dados.

## Sumário

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Execução](#execução)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelo de Entidade-Relacionamento (DER)](#modelo-de-entidade-relacionamento-der)
- [Funcionalidades](#funcionalidades)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

- [Docker](https://www.docker.com/get-started): Para executar contêineres.
- [Docker Compose](https://docs.docker.com/compose/install/): Para gerenciar multi-contêineres.
- [DBeaver](https://dbeaver.io/download/): Ferramenta de gerenciamento de banco de dados utilizada para acessar o PostgreSQL.
- [JDK 21+](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

## Instalação


Clone este repositório e navegue até o diretório do projeto:

```bash
git clone [https://github.com/israelsilva02/helpdesk-api.git]
cd helpdesk-api
```

Use o Maven para compilar o projeto e baixar as dependências necessárias:
```bash
mvn clean install
```
## Execução
### 1. Docker e PostgreSQL: Certifique-se de que o Docker está em execução. No diretório raiz do projeto, execute o seguinte comando para iniciar os contêineres Docker:
```
docker-compose up -d
```
Este comando criará e iniciará contêineres para sua aplicação e banco de dados PostgreSQL.


### 2. Configurar DBeaver: Utilize o DBeaver para conectar ao banco de dados PostgreSQL criado no Docker.
```
Host: localhost
Porta: 5432
Usuário: conforme especificado no docker-compose.yml
Senha: conforme especificado no docker-compose.yml
```
### 3. Iniciar a Aplicação: Com o contêiner do banco de dados PostgreSQL em execução, inicie sua aplicação Spring Boot:
```
mvn spring-boot:run
```
A API estará disponível em http://localhost:8080.


## Tecnologias Utilizadas
```
Spring Boot: Framework principal para construção da API.
Spring Security: Proteger as APIs com autenticação e autorização.
PostgreSQL: Banco de dados relacional utilizado.
Docker: Para empacotamento e distribuição do aplicativo.
Hibernate/JPA: Para mapeamento objeto-relacional (ORM).
Lombok: Para redução de código boilerplate.
SLF4J: Para logging.
JUnit: Para testes unitários.
```
## Estrutura do Projeto

A estrutura do projeto segue a convenção padrão do Spring Boot com um foco em segurança:
```
com.helpdesk.api: Pacote raiz do projeto.
controller: Contém os controladores da API que gerenciam as requisições.
service: Classe de serviços onde a lógica de negócios é implementada, incluindo segurança.
model: Contém as classes de modelo/entidade.
repository: Interfaces para acesso ao banco de dados.
security: Classes e configurações relacionadas à segurança.
exception: Define as exceções customizadas.
util: Contém constantes e utilitários gerais.
```
## Modelo de Entidade-Relacionamento DER

O modelo de entidade-relacionamento representa as tabelas e seus relacionamentos no banco de dados PostgreSQL:
![Captura de Tela 2024-12-13 às 12 04 28](https://github.com/user-attachments/assets/20c04254-763c-4062-88a2-1343ef7412df)
```
tb_usuario: Armazena informações dos usuários.
tb_chamado: Detalhes dos chamados de suporte.
tb_equipamento: Informações sobre equipamentos.
tb_balcao: Dados dos balcões de atendimento.
tb_atendente_balcao: Registro dos atendentes dos balcões.
tb_horarios_disponiveis: Horários disponíveis para atendimento.
```
## Funcionalidades

A API oferece uma variedade de funcionalidades para gerenciar usuários, chamados e balcões:

Usuários:
```
Criar um novo usuário;
Listar todos os usuários;
Buscar usuário por ID;
Atualizar dados de usuário;
Excluir usuário;
```
Chamados:
```
Criar um novo chamado;
Listar todos os chamados;
Buscar chamado por ID;
Atualizar detalhes de um chamado;
Excluir um chamado;
```
Balcões:
```
Criar um novo balcão
Listar todos os balcões
Buscar balcão por ID
Atualizar informações do balcão
Excluir um balcão
```
## Contribuíção 
```
Faça um fork do projeto.
Crie uma branch com a sua feature: git checkout -b minha-feature
Faça o commit das suas alterações: git commit -m 'feat: Minha nova feature'
Envie para a sua branch: git push origin minha-feature
Abra um Pull Request
