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

```bash
./mvnw compile quarkus:dev
