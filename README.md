# Projeto Teste Programador

## Criar uma solução que atenda os critérios abaixo:
Criar um serviço que receba pedidos no formato xml e json com 6 campos:
número controle - número aleatório informado pelo cliente.
data cadastro (opcional) 
nome - nome do produto
valor - valor monetário unitário produto
quantidade (opcional) - quantidade de produtos.
codigo cliente - identificação numérica do cliente.

Critérios aceitação e manipulação do arquivo:
O arquivo pode conter 1 ou mais pedidos, limitado a 10.
Não poderá aceitar um número de controle já cadastrado.
Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.
Caso a quantidade não seja enviada considerar 1.
Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a partir de 10 aplicar 10% de desconto no valor total.
O sistema deve calcular e gravar o valor total do pedido.
Assumir que já existe 10 clientes cadastrados, com códigos de 1 a 10.

Criar um serviço onde possa consultar os pedidos enviados pelos clientes.
Critérios aceitação:
O retorno deve trazer todos os dados do pedido.

filtros da consulta:
número pedido, data cadastro, todos

## Tecnologias Utilizadas
* Java 17
* Sptring MVC
* Spring Boot 3.3.0
* Spring Data
* MySQL
* Junit 5
* Json e XML
* Swagger OpenAPI

## Execução
Execute os script de banco para criar a base 
 Dados de Acesso da Base
 * spring.mvc.pathmatch.matching-strategy=ant_path_matcher
 * spring.datasource.url=jdbc:mysql://localhost:3306/product-api
 * spring.datasource.username=root
 * spring.datasource.password=1234
 * spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
 * spring.jpa.hibernate.ddl-auto=update
 * spring.profiles.active=test
 * spring.jpa.open-in-view=true

Os dados estão contidos no arquivo de properties
  Faça a Build do projeto mvn install
  Execute a aplicação em um Container
## EndPoints criados para atender a solicitação
* client-controller
  * GET/clients/{id}
  * PUT/clients/{id}
  * DELETE/clients/{id}
  * GET/clients
  * POST/clients
* request-controller
  * POST/request
  * GET/request/pedidos
  * GET/request/pedido/{idPedido}
  * GET/request/cliente/{idClient}
