# 📘 Quarkus Feed Platform - Full Stack Application com Ports and Adapters (Hexagonal Architecture)

<!-- BADGES DINÂMICOS (Substitua SEU_USUARIO e SEU_REPOSITORIO pelos seus dados reais do GitHub) -->
[![CI - Backend](https://github.com/mario-russo/quarkuspublish/actions/workflows/test-build.yaml/badge.svg)](https://github.com/mario-russo/quarkuspublish/actions/workflows/test-build.yaml)
[![GHCR Version](https://img.shields.io/badge/GHCR-latest-blue?logo=github)](https://github.com/mario-russo/quarkuspublish/pkgs/container/quarkuspublish%2Fbackend-quarkus)
![Java Version](https://img.shields.io/badge/Java-21-blue)
![Quarkus Version](https://img.shields.io/badge/Quarkus-3.1.0-blue)
![Vue Version](https://img.shields.io/badge/Vue-3.x-green)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)

Plataforma *full-stack* de publicações com arquitetura ponta a ponta, composta por uma API backend desenvolvida em Java com Quarkus sob os princípios da *Ports and Adapters (Hexagonal Architecture)* e uma aplicação frontend moderna construída em Vue 3. O ecossistema conta com autenticação e autorização via JWT, banco de dados PostgreSQL gerenciado pelo Supabase, testes automatizados robustos e esteiras automatizadas de CI/CD via GitHub Actions com deploy contínuo na Vercel e Render.

---

## 🚀 Funcionalidades

- **Gerenciamento de Usuários:** Cadastro, autenticação e emissão de tokens seguros via JWT.
- **Interação Social:** Criação de publicações, sistema de curtidas e comentários em tempo real.
- **Perfis de Usuários:** Separação dinâmica entre visibilidade de perfil público e privado.
- **Distribuição de Conteúdo:** Feed global consolidado para exibição das publicações da plataforma.

---
### 📊 Resultados dos Testes de Carga (Benchmark)

Os testes foram executados na rota `/feed/global` utilizando a ferramenta `wrk` para avaliar o comportamento do ecossistema gratuito (Vercel + Render + Supabase) sob diferentes níveis de estresse.

#### 📉 Cenário 1: Carga Nominal (Simulação de Usuários Reais)
* **Configuração:** 2 threads e 10 conexões simultâneas por 30 segundos.
* **Resultado bruto:**
```text
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    29.80ms   17.44ms 300.62ms   91.15%
    Req/Sec   177.81     43.29   260.00     72.17%
  Latency Distribution
     50%   24.93ms
     75%   32.03ms
     90%   44.65ms
     99%  101.47ms
  10639 requests in 30.04s, 17.44MB read
Requests/sec:    354.20
Transfer/sec:    594.61KB
```
* **Análise:** O sistema respondeu quase instantaneamente. Média de latência excelente de **29.80ms** com vazão estável de **354.20 requisições por segundo**. Zero erros ou timeouts.

#### 🚨 Cenário 2: Alta Concorrência Sem Otimização de Pool (Gargalo do Banco)
* **Configuração:** 4 threads e 100 conexões simultâneas por 30 segundos batendo direto no backend.
* **Resultado bruto:**
```text
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    56.94ms   26.55ms 433.98ms   75.53%
    Req/Sec   440.51    170.79   790.00     67.73%
  Latency Distribution
     50%   52.40ms
     75%   71.11ms
     90%   86.72ms
     99%  153.54ms
  23407 requests in 30.03s, 22.42MB read
  Socket errors: connect 4020, read 100, write 0, timeout 0
  Non-2xx or 3xx responses: 12175
Requests/sec:    779.33
Transfer/sec:    764.35KB
```
* **Análise:** O sistema colapsou devido a limitações físicas da infraestrutura. Ocorreram **12.175 erros HTTP (52,01% de falhas)** e 4.020 erros de socket. Enquanto a CPU do Quarkus operava estável em 13% com 60MB de RAM, a memória RAM do Supabase Free saturou e o proxy ativou o modo *Fail-Fast* por estourar o limite de conexões.

#### 🏆 Cenário 3: Resiliência Máxima Com Otimização de Pool
* **Configuração:** 2 threads e 50 conexões simultâneas por 1 minuto (Pool do Quarkus limitado cirurgicamente para 14: `max-size=14`).
* **Resultado bruto:**
```text
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   667.39ms  254.97ms   1.96s    74.00%
    Req/Sec    40.76     30.92   222.00     86.03%
  Latency Distribution
     50%  630.46ms
     75%  792.52ms
     90%  966.31ms
     99%    1.64s 
  4326 requests in 1.00m, 10.62MB read
  Socket errors: connect 0, read 0, write 0, timeout 50
Requests/sec:     72.02
Transfer/sec:    181.04KB
```
* **Análise:** Sucesso absoluto de resiliência. Foram processadas **4.326 requisições com ZERO erros de código (0 Non-2xx)**. Houve apenas 50 timeouts (1,1%) devido ao tempo na fila. O Quarkus atuou como amortecedor, enfileirando o tráfego excedente e repassando de forma ordenada para proteger o banco de dados.

---

### 💡 Conclusão

A combinação de Java Quarkus na Render, Vue 3 na Vercel e PostgreSQL no Supabase entrega um resultado fantástico no plano free para validação de ideias e MVPs. 

A grande lição é que o primeiro ponto de atenção deve ser o banco de dados (especialmente a memória RAM), já que a CPU do backend quase não foi utilizada. Não é uma arquitetura para escala infinita, mas é uma estratégia excelente para rodar um MVP com custo zero, desde que monitorada de perto.

---

## ⚙️ Tecnologias Utilizadas

| Tecnologia / Conceito | Descrição |
| :--- | :--- |
| ✅   **Java 21** | Linguagem de programação principal estável e fortemente tipada. |
| ✅   **Quarkus** | Framework de alto desempenho focado em soluções *Cloud Native Java*. |
| ✅   **Vue 3** | Framework progressivo para a construção da interface do usuário (Frontend). |
| ✅   **Clean Architecture** | Padrão arquitetural focado no desacoplamento e isolamento das regras de negócio. |
| ✅   **PostgreSQL** | Banco de dados relacional robusto utilizado para a persistência da aplicação. |
| ✅   **Supabase** | Backend-as-a-Service (BaaS) utilizado para hospedar o banco de dados PostgreSQL em nuvem. |
| ✅   **Hibernate ORM (Panache)** | Camada de persistência simplificada com o padrão Active Record / Repository. |
| ✅   **Flyway** | Ferramenta de versionamento e migração automatizada de esquemas de banco de dados. |
| ✅   **SmallRye JWT** | Implementação de segurança padrão para autenticação e autorização via tokens *stateless*. |
| ✅   **JUnit 5 & Mockito** | Frameworks padrão da indústria para criação de asserções e isolamento de testes. |
| ✅   **Testcontainers** | Gerenciamento de containers Docker dinâmicos em tempo de teste para simulação real de banco de dados. |
| ✅   **Docker & Compose** | Containerização completa e orquestração ágil do ambiente de desenvolvimento local. |
| ✅   **GitHub Actions** | Automação completa da esteira de CI/CD para compilação, validação e qualidade. |
| ✅   **Render** | Plataforma em nuvem utilizada para o deploy automatizado e hospedagem da API Quarkus. |
| ✅   **Vercel** | Plataforma de nuvem otimizada para o deploy contínuo da aplicação Vue 3. |
| ✅   **GraalVM (Native Image)** | Suporte à compilação nativa gerando tempos de inicialização na casa dos milissegundos. |
| ✅   **Ports and Adapters (Hexagonal Architecture)** | Separação entre domínio, casos de uso e adaptadores de entrada e saída. |

---

## 🏗️ Arquitetura


O projeto é uma rede social desenvolvida em Java com Quarkus, seguindo os princípios de  **Ports and Adapters (Hexagonal Architecture)**. A estrutura foi projetada para manter as regras de negócio desacopladas das tecnologias externas, facilitando manutenção, testes e evolução da aplicação.

A arquitetura é dividida em:

- **Domain**: entidades e regras centrais do negócio.
- **Application**: casos de uso e portas de entrada e saída.
- **Adapters In**: camada responsável por receber requisições HTTP e autenticação.
- **Adapters Out**: comunicação com banco de dados e demais recursos externos.
- **Persistence**: implementação dos repositórios utilizando PostgreSQL e Hibernate Panache.


```
src/main/java/com/br/mariorusso

├── domain
│   └── model
│
├── application
│   ├── port
│   │   ├── in
│   │   └── out
│   └── usecase
│
├── adapter
│   ├── in
│   │   ├── auth
│   │   └── rest
│   └── out
│       └── persistence
│           ├── entity
│           └── repository
│
└── resources
```

### Fluxo da aplicação

```text
HTTP Request
     ↓
REST Controller (Adapter In)
     ↓
Use Case
     ↓
Port Out
     ↓
Repository Adapter
     ↓
PostgreSQL
```
---

## 🧪 Estratégia de Testes Automatizados

A estabilidade e a qualidade do código são garantidas através de uma abordagem de testes robusta:

* **Testes de Unidade (JUnit 5 & Mockito):** Isolamento total dos casos de uso (`Use Cases`) e entidades de domínio, simulando dependências externas através de mocks para validação rápida de regras complexas.
* **Testes de Integração (Testcontainers):** Validação real de componentes de infraestrutura, onde containers **Docker** reais do **PostgreSQL** são inicializados dinamicamente durante a execução da suíte de testes.

---

---

## 🗄 Banco de Dados

Estrutura relacional hospedada no Supabase utilizando PostgreSQL.

<img width="1015" height="858" alt="Screenshot from 2026-06-16 12-00-35" src="https://github.com/user-attachments/assets/bf44d4ff-b50a-4c1e-8b7b-90885553a3bc" />

### Principais Entidades

* Usuario
* Publicacao
* Comentario
* Curtida

---
---
## 📸 Demonstração
### Tela de Login

<img width="964" height="949" alt="Screenshot from 2026-06-16 00-44-53" src="https://github.com/user-attachments/assets/5fa4aced-e8c5-4937-89ee-1bb474c1ef4c" />

### Cadastro

<img width="964" height="949" alt="Screenshot from 2026-06-16 00-49-22" src="https://github.com/user-attachments/assets/d62ebf6e-75b8-400c-bbd3-1aecde1c35e6" />

### Feed Global - Curtida - Comentário

<img width="1840" height="999" alt="Screenshot from 2026-06-16 11-57-18" src="https://github.com/user-attachments/assets/1f3e6672-bfb2-4145-86d4-c95a39118345" />

### Perfil

<img width="1840" height="999" alt="Screenshot from 2026-06-16 11-57-41" src="https://github.com/user-attachments/assets/6a4c812a-f2b8-4b87-b5d2-aae90fc17952" />

### Perfil publico

<img width="1840" height="999" alt="Screenshot from 2026-06-16 11-57-32" src="https://github.com/user-attachments/assets/073d6525-40cd-49b2-bfc8-2d3d8d502cd2" />


### Editar Perfil

<img width="1840" height="999" alt="Screenshot from 2026-06-16 11-57-47" src="https://github.com/user-attachments/assets/2c94944c-c8b1-4e0b-9657-e2f65bbd4f2d" />

---

## 📦 Como Rodar Localmente

### Pré-requisitos
- Java 21
- Maven
- Docker e Docker Compose instalado

### Executar a aplicação completa via Docker:
Para subir o ecossistema completo (Backend e Frontend sincronizados) com um único comando, execute na raiz do projeto:

```bash
docker compose up --build
```
Após o build inicial, o frontend estará acessível em: **`http://localhost:9000/`**

---

## 🔧 Configuração de Variáveis de Ambiente (.env)

### 1. Variáveis para o Backend
O backend está configurado para se conectar a um banco de dados relacional. Você deve criar um arquivo `.env` na pasta do backend seguindo o modelo abaixo (caso as chaves não sejam preenchidas, o sistema tentará adotar os valores padrões do projeto). 

O backend local responde na porta **`http://localhost:8080`**.


```env
KIND=postgresql
DB_USER=mario-russo
DB_PASSWORD=mario-russo
DB_URL=jdbc:postgresql://localhost:5432/banco

PUBLIC_KEY=classpath:secrets/publicKey.pem
PRIVATE_KEY=classpath:secrets/privateKey.pem
```

### 2. Variáveis para o Frontend
Crie um arquivo `.env` na pasta do frontend apenas para apontar o endereço da API que o Vue 3 deve consumir:

```env
VITE_API_URL=http://localhost:8080/
```

---
Desenvolvido por [Mário Russo](https://github.com).


