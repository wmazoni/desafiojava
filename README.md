# Desafio Java Concrete Solutions



## Endpoint Login
* Esse endpoint recebe um email e senha válidos para acesso e gera um token no cabeçalho da requisição.

```json
{
    "email" : "wmazoni@protonmail.com",
    "password" : "batata"
}
```

## Endpoint Cadastro
* Esse endpooint recebe o json solicitado para cadastro, apenas de um token do usuário admin autenticado.

```json
    {
        "name": "João da Silva",
        "email": "joao@silva.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "987654321",
                "ddd": "21"
            }
        ]
    }
```
## URL para teste do banco de dados em memória H2

http://localhost:8080/h2-console

## Link para o projeto no Heroku
https://desafio-java-concrete.herokuapp.com/



