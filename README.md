# MeliChallenge

MeliChallenge é o projeto resultado do desafio técnico do Mercado Livre para atuação como Backend Engineer.


## O projeto

Trata-se de uma API nos padrões REST que irá prover as funcionalidades necessárias para se fazer o gerenciamento de Usuários de uma aplicação.

### Requisitos funcionais

A API atualmente contempla as seguintes funcionalidades:

- [x] Cadastro de Usuários
- [x] Atualização completa ou parcial dos dados do usuário
- [x] Busca de um usuário através do seu respectivo UUID
- [x] Listagem de usuários

#### Regras de negócio:

1. Para o cadastro de usuário:

- [x] Valida que somente usuários acima de 18 anos possam ser inputados no sistema. Essa regra se baseia no atributo data de nascimento do usuário.
- [x] Valida que o email do usuário é único.
- [x] Valida que o CPF do usuário é único.
- [x] Valida que o CPF fornecido pelo usuário é um CPF válido que atende ao cálculo de verificação de dígitos. Explicação do algoritmo utilizado como base para implementação em: LINK DO MACORATTI

2. Para a atualização completa ou parcial:

- A atualização dos dados do usuário pode ser feita de maneira total ou parcial. Isso implica que todos os valores que forem informados na API de atualização, serão atualizados e os campos que forem omitidos serão ignorados. Para se apagar o valor de um dos atributos do usuário, a solicitação deve ser feita de maneira explícita.

3. Para a listagem de usuários:

- A listagem de usuários possui as propriedades **page** e **size** para controle de paginação dos usuários.
- Essas propriedades estão encapsuladas dentro de um Query Parameter chamado **filters**.
- Por padrão, page é definido com o valor 0 e size com o valor 10. Esses valores podem ser alterados na requisição GET do recurso api/v1/users. 
- A listagem de usuário pode ainda receber em filters um atributo chamado **name**, que pode ser usado para atribuir um filtro na listagem com base no nome do usuário.
- O filtro por nome de usuário poderá ser feito por String parcial, ou seja, se há um usuário com o nome 'João Pedro', um busca pela String 'pedro' deve retornar o registro João Pedro como resultado da listagem.
- Por padrão, os usuários retornados na listagem serão ordenados alfabeticamente pelo atributo nome.


### Requisitos não funcionais

- A API pode ser executada através de containers Docker
- A API faz uso de um banco de dados relacional para a persistência de seus dados que também pode ser executada via container Docker.
