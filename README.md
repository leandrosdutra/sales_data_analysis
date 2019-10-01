# Controle de análise de dados de vendas

### Descrição
Sistema responsável pela análise de dados de venda através da 
importação de lotes de arquivos disponibilizados em um diretório de entrada padrão.

Para cada arquivo disponilizado no diretório de entrada padrão, é gerado 
um arquivo com as informações listadas abaixo e disponibilizado no diretório de saída padrão:

- Número de clientes existentes no arquivo
- Número de vendedores existentes no arquivo
- Identificador da maior venda contida no arquivo
- Nome do pior vendedor contido no arquivo

### Diretórios padrões dos arquivos
- Entrada: "user.home"/data/in
- Saída: "user.home"/data/out

### Gerenciador de Dependências e Ferramenta de Build
- Gradle 5.6.2

### Versão do JDK
- Java 11

### Bibliotecas externas utilizadas
- org.projectlombok:lombok:1.18.10 - Biblioteca Java utilizada para redução de 
código fonte nos projetos(https://projectlombok.org/)
- ma.glasnost.orika:orika-core:1.5.4 - Biblioteca utilizada para mapeamento bean de 
forma leve e rápida(https://orika-mapper.github.io/orika-docs/intro.html)

### Solução adotada para o projeto
Inicialmente todos os arquivos pré existentes no diretório de entrada 
padrão são processados e seus respectivos arquivos de saída gerados no diretório de saída padrão.
Após essa varredura inicial, foi utilizada a interface pública Watch Service do pacote java.nio.file
que funciona como uma espécie de monitor de eventos em um diretório especificado.
O Watch Service(https://docs.oracle.com/javase/7/docs/api/java/nio/file/WatchService.html) permite que sejam sinalizados os eventos que devem ser monitorados, para essa solução, usei os seguintes:
- StandardWatchEventKinds.ENTRY_CREATE - Gera um evento a cada criação de um novo arquivo no diretório monitorado.
- StandardWatchEventKinds.ENTRY_MODIFY - Gera um evento a cada alteração de um arquivo existente no diretório monitorado.

Dessa maneira, a cada inclusão ou alteração de um arquivo no diretório de 
entrada padrão que está sendo monitorado, é disparado o processamento responsável pela leitura do arquivo
e posteriormente a criação do árquivo de saída.

### Exemplo de arquivo de entrada

Dados do vendedor Os dados do vendedor possuem o identificador 001 e seguem o seguinte formato: <p>
001çCPFçNameçSalary

Dados do cliente Os dados do cliente possuem o identificador 002 e seguem o seguinte formato:<p>
002çCNPJçNameçBusiness Area
 
Dados de venda Os dados de venda possuem o identificador 003 e seguem o seguinte formato:<p>
003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name 
 
001ç00000000001çPedro1ç3712.14<p>
002ç00000000000001çJoao1çRural<p>
003ç1ç[1-2-21.96,2-1-6.38]çPedro1<p>

### Exemplo de arquivo de saída

Number of Customers: 1000<p>
Number of Sellers: 1000<p>
Biggest Sale Id: 307<p>
Worst Seller Name: Pedro68<p>

### Testes de performance

Foram gerados três arquivos para testes de performance, com as seguintes características:

- Arquivo de 3000 linhas, composto da seguinte maneira:
   - 1000 clientes
   - 1000 vendedores
   - 1000 vendas, cada um com 3 itens vinculados
   - `Tempo de processamento: Inferior a 2 segundos`

- Arquivo de 15000 linhas, composto da seguinte maneira:
   - 5000 clientes
   - 5000 vendedores
   - 5000 vendas, cada um com 3 itens vinculados
   - `Tempo de processamento: 6 segundos`

- Arquivo de 30000 linhas, composto da seguinte maneira:
   - 10000 clientes
   - 10000 vendedores
   - 10000 vendas, cada um com 3 itens vinculados
   - `Tempo de processamento: 19 segundos`

### Comando para execução

gradle bootRun