# PooGestaoDeEstoque
projeto para a disciplina de POO

Versao extra(sem persistencia de dados porem com mais implementacoes do conteudo dado durante o quadrimestre) disponivel em https://github.com/learning333/poogestaodeestoqueextra



Rodando localmente:

  Atraves do maven via 
  ./mvnw spring-boot:run

  É possivel acessar o prototipo do formulario web de cadastro de produto via http://localhost:8080/. A Parte web não foi implementada alem desse formulario.
  
Uso do programa:
  O uso do programa se da via linha de comando atraves dos menus de produto, compras e vendas.
  
  Existem 3 entidades no programa:
    Produto: entidade basica, deve ser cadastrado antes de qualquer operacao de compra ou venda
    
    LoteCompra: Pedido de compra de um unico produto com quantidade e preco unitario variavel. Deve ter o recebimento confirmado antes de ficar disponivel para o menu de vendas
    
    Venda:Venda de um unico produto limitado a quantidade disponivel no lote de origem do mesmo. Pode ser devolvida retornando a quantidade do item para o estoque.
  
 Exemplo de uso:
    Menu 1-1 Cadastrar Produto
      Para cadastrar um produto digita se o nome e descricao, ex: "caneta azul" , "caneta esferografica com tampa".
      
     Menu 2-1 Nova Compra
      Entrar com data da compra, uma String de referencia para o pedido, em seguida o programa exibe uma lista de produtos cadastrados para que o usuario escolha o produto do pedido de compra
      Ao entrar com um id de produto valido o programa pede quantidade, e valor unitario no formato 1,0.
      
     Menu 2-3 Recebimento
      O programa mostra uma lista de pedidos de compra cujo status é "em transito" e apos a entrada do id do pedido altera o campo status para "em maos" liberando o lote para venda
      
     Menu 3-1 Vendas 
      O programa mostra uma lista de lotes com status "em maos" e apos entrada do id do lote valido permite o preenchimento das informações do pedido de venda: quantidade, preco unitario e nome do cliente. 
      
     Menu 3-4 Devolucao
      O programa mostra uma lista de vendas com status "normal" e apos entrada do id de venda valido altera o status da venda para "devolvido" e devolve para o loteCompra a quantidade referente ao pedido.
 
     As outras funcoes nao citadas servem para visualizacao dos produtos, lotes e vendas.
 
 Licença 
  
  PooGestaoDeEstoque is licensed under the MIT License.
  
  




