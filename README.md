# TaskManager
CRUD de tarefas com JSF

## Tecnologias usadas
### Linguagem de programação
- Java
### Framework MVC
- Java Server Faces (JSF)
### Banco de Dados
- PostgreSQL
### Outras tecnologias
- Java Persistence API (JPA)
- Spring Configuration
- Maven

## Observações do projeto
Iniciamente decidi fazer um projeto maven, pois baixar as dependências e colocar no projeto fica bem mais fácil. Coloquei algumas configurações com Spring, para manusear bem as configurações do projeto e do banco de dados. O create, delete e update foram relativamente rapidos e faceis de fazer, cada um com sua especificidade. O read foi o mais demorado e difícil, pois havia vários campos que poderia pesquisar, meu algoritmo de pesquisa se baseia primariamente no ID, se existir o ID, ele retorna. Depois vem os outros campos, e se responsável e situação é a mesma vai para a lista. Título/Descrição é a parte mais complexa, se o título for o mesmo vai para lista, senão vai buscar palavra por palavra, achou uma palavra igual ele já coloca na lista.
