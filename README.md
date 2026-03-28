# 📘 Quarkus Publish API

API backend desenvolvida em Java com Quarkus para uma plataforma de publicações com autenticação via JWT, arquitetura Clean, testes automatizados e integração contínua com Docker e GitHub Actions.

---

## 🚀 Funcionalidades

- Registro e login de usuários
- Publicação de posts
- Curtidas e comentários
- Autenticação via JWT
- Camadas separadas por responsabilidade
- Testes de unidade com JUnit e Mockito
- Integração contínua com GitHub Actions
- Docker para ambiente isolado

---

## 🧱 Arquitetura do Projeto

Este projeto segue os princípios da **Clean Architecture**, com inspiração na **Arquitetura Hexagonal (Ports and Adapters)**, promovendo desacoplamento e testabilidade.

### Camadas:

- **Domain (Núcleo)**: Entidades e interfaces que representam a lógica de negócio, independentes de frameworks.
- **Use Cases**: Casos de uso isolados que orquestram as regras do domínio.
- **Interface Adapters**:
  - **Entrada**: Controladores REST com Quarkus (`*Resource`)
  - **Saída**: Implementações de repositórios (`*RepositoryImpl`)
- **Frameworks & Drivers**: Camada externa com Quarkus, JPA (Panache), JWT, etc.

> A comunicação entre as camadas é feita via interfaces, garantindo baixo acoplamento e fácil manutenção.

---

## 🧪 Testes

O projeto utiliza **JUnit 5** e **Mockito** para testes de unidade. Os casos de uso são testados isoladamente, facilitando manutenção e refatoração.

---

## ⚙️ Tecnologias utilizadas

| Tecnologia     | Descrição                             |
|----------------|----------------------------------------|
| Java 17        | Linguagem principal                    |
| Quarkus        | Framework backend                      |
| JPA (Panache)  | Persistência de dados                  |
| JWT            | Autenticação via token                 |
| JUnit 5        | Testes de unidade                      |
| Mockito        | Mocking em testes                      |
| Docker         | Containerização                        |
| GitHub Actions | Integração contínua                    |

---

## 📦 Como rodar localmente

### Pré-requisitos

- Java 17
- Maven
- Docker (opcional)
- Quarkus CLI (opcional)

### Executar a aplicação:
 Para roda a aplicação completa bastar ter o docker e docker compose instalado
 com o comando 
```bash
docker compose up --build 

pode ser acessar em http://localhost:9000/



### Env de exemplo para o backend
a parte do backend está configurada para receber o banco de dados.
tem criando um env passando o as variavel como segui o exempplo lembando que caso ele não
tenha uma as keys usuara as que estão no projeto 
para ter acessar ao backend pode ir para http://localhost:8080
.env
KIND=postgresql
DB_USER=mario-russo
DB_PASSWORD=mario-russo
DB_URL=jdbc:postgresql://localhost:5432/banco

PUBLIC_KEY=classpath:secrets/publicKey.pem
PRIVATE_KEY=classpath:secrets/privateKey.pem

### Env para FrontEnd
env apenas para conectar ao backend

VITE_API_URL = http://localhost:8080/

para acessar a aplicação frontend pode ir para  http://localhost:9000/
