# Exercícios GraphQL - Spring Boot

Projeto baseado no exemplo da aula, com as novas queries e mutations dos exercícios.

## Como executar
1. Requisitos: JDK 17+ e, no VS Code, o *Extension Pack for Java* e o *Spring Boot Extension Pack*.
2. Abra a pasta do projeto no VS Code (File > Open Folder).
3. Rode `GraphqlApplication` (botão Run) ou, no terminal: `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).
4. Acesse o GraphiQL em http://localhost:8080/graphiql

Exemplos prontos de queries/mutations: `exemplos-graphiql.graphql`.

## O que foi implementado
**Queries** (schema em `src/main/resources/graphql/scheme.graphqls`, funções em `UserController`):
- `humans: [Human!]!`
- `starships: [Starship!]!`
- `character(id: ID!): Character` (retorna Human ou Droid)

**Mutations:**
- `createHuman(id, name, height)`
- `createDroid(id, name, primaryFunction)`
- `createStarship(id, name, length)`
- `addFriend(characterId, friendId)`

**Schema:** `Character` virou uma `interface` implementada por `Human` e `Droid`; `Starship` é um tipo à parte.
Os dados ficam em memória, com alguns registros iniciais (humanos 1-3, droids 4-5, naves 1-2).
Ids repetidos e personagens inexistentes geram um erro `BAD_REQUEST`.
