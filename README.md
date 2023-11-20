# MeliChallenge

MeliChallenge é o projeto resultado do desafio técnico do Mercado Livre para atuação como Backend Engineer.

<hr>

## O projeto

Trata-se de uma API nos padrões REST que irá prover as funcionalidades necessárias para se fazer o gerenciamento de Usuários de uma aplicação.
<hr>

### Requisitos funcionais

A API atualmente contempla as seguintes funcionalidades:

- [x] Cadastro de Usuários
- [x] Atualização completa ou parcial dos dados do usuário
- [x] Busca de um usuário através do seu respectivo UUID
- [x] Listagem de usuários

#### Regras de negócio:

1. **Para o cadastro de usuário**:
   - [x] Valida que somente usuários acima de 18 anos possam ser inputados no sistema. Essa regra se baseia no atributo data de nascimento do usuário.
   - [x] Valida que o email do usuário é único.
   - [x] Valida que o CPF do usuário é único.
   - [x] Valida que o CPF fornecido pelo usuário é um CPF válido que atende ao cálculo de verificação de dígitos. Explicação do algoritmo utilizado como base para implementação em: LINK DO MACORATTI

   
2. **Para a atualização completa ou parcial**:
   - A atualização dos dados do usuário pode ser feita de maneira total ou parcial. Isso implica que todos os valores que forem informados na API de atualização, serão atualizados e os campos que forem omitidos serão ignorados. Para se apagar o valor de um dos atributos do usuário, a solicitação deve ser feita de maneira explícita.


3. **Para a listagem de usuários**:
   - A listagem de usuários possui as propriedades **page** e **size** para controle de paginação dos usuários.
   - Essas propriedades estão encapsuladas dentro de um Query Parameter chamado **filters**.
   - Por padrão, page é definido com o valor 0 e size com o valor 10. Esses valores podem ser alterados na requisição GET do recurso api/v1/users. 
   - A listagem de usuário pode ainda receber em filters um atributo chamado **name**, que pode ser usado para atribuir um filtro na listagem com base no nome do usuário.
   - O filtro por nome de usuário poderá ser feito por String parcial, ou seja, se há um usuário com o nome 'João Pedro', um busca pela String 'pedro' deve retornar o registro João Pedro como resultado da listagem.
   - Por padrão, os usuários retornados na listagem serão ordenados alfabeticamente pelo atributo nome.

<hr>

### Requisitos não funcionais

- Faz uso de um banco de dados relacional para a persistência de dados. Pode ser usado via container Docker.

<hr>

### Arquitetura do projeto

Para esse projeto a ideia foi aplicar alguns dos princípios da Clean Architecture, visando uma aplicação separada em camadas e cada uma delas com um escopo e impactos bem definidos, diminuindo o acoplamento de código, porém tentando manter a simplicidade de uso e legibilidade. Assim, para se tentar chegar nesse resultado de uma arquitetura robusta, mas ainda de fácil entendimento, foi aplicado alguns conceitos do SOLID, sendo os principais:

- o **S**, visando criar responsabilidades únicas para cada camada da aplicação
- o **I**, com o objetivo de ter contratos simples e bem definidos 
- o **D**, visando sempre depender de abstrações e não de implementações

Com base nisso, o projeto foi estruturado da seguinte forma:

```bash
src
├── main
│   ├── java
│   │   └── com
│   │       └── java
│   │           └── meli
│   │               ├── core
│   │               │   ├── config
│   │               │   ├── mapper
│   │               │   └── utils
│   │               │       └── cpf
│   │               │           └── exceptions
│   │               └── user
│   │                   ├── application
│   │                   │   ├── dao
│   │                   │   ├── models
│   │                   │   ├── presenter
│   │                   │   ├── service
│   │                   │   │   └── impl
│   │                   │   └── usecases
│   │                   ├── domain
│   │                   ├── infrastructure
│   │                   │   └── database
│   │                   │       └── mapper
│   │                   └── presentation
│   │                       └── api
│   │                           └── v1

```

Nele há 2 pacotes principais:

- O pacote **core** que contém aspectos técnicos importantes para fazer a aplicação funcionar da forma correta, mas que não possui conhecimentos específicos de negócio. Nele estão códigos/contratos mais genéricos e de utilidades que podem ser compartilhados por outros módulos da aplicação.
- O pacote **user** que tem por objeto ter todo o conhecimento necessário da aplicação para gerenciar o recurso Usuário, dessa forma centralizando qualquer questão relacionada ao usuário nesse pacote, desde a disponibilização da API e suas rotas, a aplicação das regras de negócio requeridas para se criar e manter um usuário, até a manipulação dos dados do usuário no banco de dados.

O pacote user é onde as camadas da Clean Architecture são colocadas em prática dessa forma:

- **domain** é onde está o conhecimento do que é um User para a aplicação, tenta responder a pergunta "o que é?"
- **application** é onde está a execução das regras de negócio, interagindo com as demais camadas através de contratos definidos nessa mesma camada ou na camada de domínio, tenta responder a pergunta "como?"
- **infrastructure** é a camada que detém o conhecimento das tecnologias envolvidas no processo do software. Nesse projeto, por exemplo, é a camada que conhece o banco de dados, que sabe como se comunicar com o banco de dados e como efetuar uma consulta.
- **presentation** é a camada que apresenta o software para o mundo externo, um ponto de acesso, nesse projeto, isso é feito através da API, mas poderia se ter outros pacotes que poderiam criar novas portas de acesso às regras de negócio de Usuário.


Abaixo temos um diagrama para ilustrar as camadas e sua composição:

![Diagrama ilustrando as camadas do projeto e suas fronteiras](/doc/images/api_onion_diagram.jpg "Diagrama de camadas do projeto")

- O diagrama mostra que quanto mais internos seus componentes são, menos dependências eles possuem, ou seja, a camada mais acima não depende de ninguém e conforme vamos descendo as camadas, essas camadas só podem depender das que estão acima.
- É comum vermos abstrações (classes abstratas ou interfaces) numa camada superior e vermos sua implementação estar numa camada inferior, pois é justamente a abstração que nos permite fazer a comunicação entre as camadas
- Presentation e infrastructure estão no mesmo degrau da hierarquia de camadas, mas não possuem dependência entre si


### Instalação e execução

#### Tecnologias utilizadas

- Java 17
- Gradle 7.4
- Spring Boot 3.1.5
- Docker com Docker Compose
- Postgres (rodando em contêiner Docker)

<hr>

#### Execução

Para a execução, basta fazer o clone do projeto, acessar o diretório em que o projeto foi clonado ou baixado e executar 
o seguinte comando no terminal:


```bash
docker compose -f compose-uat.yaml up
```

Após a execução, podemos verificar se os contêineres estão em execução através do comando **docker ps**:

O resultado deve ser algo assim:

```bash
➜  meli git:(main) ✗ docker ps
CONTAINER ID   IMAGE             COMMAND                  CREATED          STATUS          PORTS                                       NAMES
baab06e18603   meli-meli_app     "java -jar build/lib…"   28 seconds ago   Up 27 seconds   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   meli_app
05ed23ad2123   postgres:latest   "docker-entrypoint.s…"   28 seconds ago   Up 27 seconds   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp   postgres-db
```

### Usando o projeto

Se tudo deu certo, podemos acessar o navegador no seguinte endereço:

```
http://localhost:8080/docs
```

Através dessa interface do OpenAPI é possível verificar os endpoints disponíveis e testar as execuções de cada um deles. 


#### Considerações importantes da API:

- As datas trafegadas nos endpoints devem estar no formato: yyyy-MM-dd
- O CPF não deve ser informado com a formatação. A API retornará uma mensagem e status de erro informando ao usuário para se certificar que o CPF não esteja formatado

OBS.: Tentei documentar todas essas especificidades direto no OpenAPI, mas na versão utilizada pela aplicação, o RequestBody, por exemplo, ainda não aceita uma descrição (só encontrei em versões mais novas).


#### Minhas considerações do projeto:

O projeto foi bem bacana de ser desenvolvido ainda mais que estava há tempo sem codificar no ecossistema Java/Spring, 
até por conta disso optei por focar mais no desenho da arquitetura proposta e nas relações entre as camadas do sistema
do que em otimizações da linguagem em si. Acredito que há muitos pontos de melhoria no projeto e novas características 
da linguagem que posso não ter usado tanto quanto deveria, mas a experiência foi muito legal. 
Apesar de parecer um projeto de uma API simples, há complexidades bem interessantes 
como escolher o tipo Date adequado para a realização dos cálculos de idade mínima requerida e, principalmente, a 
atualização de dados parcial.


### ToDos / Ideias e Melhorias para o projeto
- Adicionar mais testes à aplicação como um todo, principalmente teste unitários para validar os casos de uso e de integração para validar os contratos da API.
- Melhorar a coleta e a visualização de logs da aplicação, talvez adicionando alguma ferramenta externa para permitir um melhor monitoramento de seu desempenho
- Atualmente na listagem de Usuários, é possível fazer busca por texto no campo nome de forma parcial. Essa restrição é feita diretamente no banco de dados, mas seria ideal usar um recurso mais apropriado para buscas por texto parcial, como um Elasticsearch, por exemplo.
