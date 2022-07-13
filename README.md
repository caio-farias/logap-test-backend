# logap-test-backend
#### Aplicação rodando: https://logap-test-frontend.herokuapp.com/
  O Heroku coloca as aplicações para dormir, então vai demorar um pouquinho para o backend retornar os dados.
  
## Endpoints

### Categoria de Produto

- Cadastrar categoria de produtos
    * Endpoint (POST): https://logap-test-backend.herokuapp.com/product-category
    
- Ler categoria de produtos por id
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/product-category/ID
    
 - Ler todas as categorias de produtos
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/product-category
   
- Atualizar categoria de produtos
    * Endpoint (PATCH): https://logap-test-backend.herokuapp.com/product-category/ID

- Deletar categoria de produtos
    * Endpoint (DELETE): https://logap-test-backend.herokuapp.com/product-category/ID

### Produtos
- Cadastrar produto
    * Endpoint (POST): https://logap-test-backend.herokuapp.com/product/ID_CATEGORIA/ID_FORNECEDOR

- Ler produto por id
    * Endpoint (POST): https://logap-test-backend.herokuapp.com/product/ID
 
- Atualizar produto
    * Endpoint (PATCH): https://logap-test-backend.herokuapp.com/product/ID

- Ler todos os produtos de um fornecedor
  * Endpoint (GET): https://logap-test-backend.herokuapp.com/product/owned/ID_FORNECEDOR

- Ler todos os produtos que não são de um fornecedor
  * Endpoint (GET): https://logap-test-backend.herokuapp.com/product/not-owned/ID_FORNECEDOR

- Deletar produto
    * Endpoint (DELETE): https://logap-test-backend.herokuapp.com/product/ID

### Fornecedores de produtos
- Cadastrar fornecedor
    * Endpoint (POST): https://logap-test-backend.herokuapp.com/product-supplier

- Ler fornecedor por id
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/product-supplier/ID 
 
- Ler todos os fornecedores
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/product-supplier/

- Atualizar fornecedor
    * Endpoint (PATCH): https://logap-test-backend.herokuapp.com/product-supplier/ID

- Adicionar novos produtos sem fornecedores à fornecedor
    * Endpoint (PATCH): https://logap-test-backend.herokuapp.com/product-supplier/add/ID

- Remover produtos de fornecedor
    * Endpoint (PATCH): https://logap-test-backend.herokuapp.com/product-supplier/remove/ID
    
- Deletar fornecedor
    * Endpoint (DELETE): https://logap-test-backend.herokuapp.com/product-supplier/ID
    
### Relatórios

- Ler categoria de produtos em falta
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/report/product-supplier 

- Ler produtos com falta
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/report/product  
  
- Ler fornecedores com produtos em falta
    * Endpoint (GET): https://logap-test-backend.herokuapp.com/report/product-supplier
