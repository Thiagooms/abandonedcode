# AbandonedCode

Sistema de blog desenvolvido com Spring Boot aplicando arquitetura hexagonal e princípios de Domain-Driven Design.

**Links do projeto:**
- API Backend: https://abandonedcode-api.fly.dev
- Frontend: https://front-abandoned-code.vercel.app
- Documentação (Swagger): https://abandonedcode-api.fly.dev/swagger-ui.html

## Contexto

Este projeto nasceu como uma forma de estudar e aplicar conceitos de arquitetura de software que vejo sendo discutidos no mercado, mas que raramente tive oportunidade de implementar em projetos reais. A ideia era sair da teoria e realmente entender como funcionam padrões como Hexagonal Architecture e DDD na prática, com suas vantagens e trade-offs.

Escolhi criar um sistema de blog porque é um domínio que todo mundo conhece, o que me permite focar na arquitetura ao invés de ficar quebrando a cabeça com regras de negócio complexas.

## Por que Arquitetura Hexagonal + DDD?

A decisão de usar esses padrões não foi por hype, mas por curiosidade técnica. Queria entender:

- Como organizar um projeto onde as regras de negócio ficam realmente isoladas da infraestrutura
- O que acontece quando você separa use cases de controllers
- Se vale a pena ter mappers em múltiplas camadas
- Como testar um domínio sem depender de frameworks

O resultado foi um projeto com mais boilerplate do que um CRUD tradicional precisaria, mas que me deu uma visão muito mais clara de separação de responsabilidades e testabilidade.

## Arquitetura

O projeto segue uma estrutura em camadas bem definida:

**Domain (núcleo):**
Entidades puras (`Post`, `Category`), Value Objects (`Slug`, `Content`), eventos de domínio e interfaces (ports) que definem contratos. Não há dependências de framework aqui.

**Application:**
Implementa os casos de uso do sistema através de services. É aqui que orquestro lógica de negócio, valido regras e coordeno chamadas entre domain e infrastructure.

**Infrastructure:**
Adapters que implementam os ports definidos no domain. Aqui está tudo que é detalhe técnico: JPA, PostgreSQL, mapeamentos.

**Presentation:**
Controllers REST que expõem a API. Trabalham com DTOs e delegam tudo para a camada de application.

A separação foi útil principalmente na hora de testar lógica de negócio sem subir banco de dados ou servidor HTTP.

## Stack Técnica

**Backend:**
- Java 21
- Spring Boot 4.0.0
- PostgreSQL
- Flyway (migrations)
- Swagger/OpenAPI

**Frontend:**
- React 19 + TypeScript
- Vite
- React Query
- Axios
- React Router

**Deploy:**
- Backend + Database: Fly.io
- Frontend: Vercel

## Rodando localmente

**Backend:**

```bash
# Configurar PostgreSQL local e ajustar application.yml com suas credenciais

./mvnw clean install
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

**Frontend:**

```bash
cd front-abandoned-code
npm install
npm run dev
```

O frontend estará em `http://localhost:5174`

## Endpoints principais

```
GET    /api/posts              # Lista posts publicados
GET    /api/posts/{id}         # Detalhes de um post
POST   /api/posts              # Criar post (draft)
PUT    /api/posts/{id}         # Atualizar post
POST   /api/posts/{id}/publish # Publicar post
DELETE /api/posts/{id}         # Deletar post

GET    /api/categories         # Lista categorias
POST   /api/categories         # Criar categoria
DELETE /api/categories/{id}    # Deletar categoria
```

## Aprendizados

O que funcionou bem:
- Testes de domínio ficaram muito limpos, sem mocks de infraestrutura
- Mudanças em detalhes técnicos (trocar biblioteca, mudar banco) ficaram isoladas
- Código expressivo que comunica intenção de negócio

O que poderia ser diferente:
- Para um blog simples, a quantidade de código é alta demais
- Três camadas de mappers (DTO → Domain → JPA) gera muito boilerplate
- Em um projeto pequeno assim, talvez Clean Architecture tradicional fosse suficiente

No geral, foi um ótimo exercício para entender quando vale a pena aplicar esses padrões e quando eles são overkill.