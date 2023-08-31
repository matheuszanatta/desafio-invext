Abaixo estão as instruções para executar a solução implementada e realizar requisições que serão encaminhadas para a respectiva fila que cada microsserviço atende.

A solução consta com 1 lib e 4 microsserviços:
desafio-invext
consumer-card
consumer-loan
consumer-others
librabbitmq


Pré-requisitos para execução do projeto:
Docker e Docker Compose instalados para subir o container do RabbitMq.
Maven instalado para instalação da lib.
JDK mínima na versão 17 para execução do projeto.
[Recomendado] Postman para acessar a funcionalidade de lançamento de requisições.


Execução do Projeto:
1º: Através da linha de comando, navegue até o diretório raiz do projeto librabbitmq e execute o comando:
	> ./mvnw install -f pom.xml
2º: Navegue até o diretório raiz do projeto desafio-invext
3º: Execute o comando:
	> docker-compose up -d
4º: Execute o comando:
	> ./mvnw spring-boot:run
5º: Em outras abas/janelas navegue até o diretório dos microsserviços consumer-card, consumer-loan e consumer-others e execute o mesmo comando:
	> ./mvnw spring-boot:run


Criando uma nova requisição:
Para enviar uma nova requisição a ser processada e encaminhada para o RabbitMq deve ser criado um POST para o endereço http://localhost:8080/requisition com o body de exemplo abaixo:
{
	"theme": "CARD_ISSUES",
	"message": "Teste",
	"status": "Falha no processamento"
}
Os 3 theme's possíveis são "CARD_ISSUES", "LOAN_CONTRACTING" e "OTHERS".


Explicações:
O diagrama(invext-solution.drawio.png) representa o fluxo e a comunicação entre os microsserviços, broker e client.

Cada microsserviço consumidor é iniciado com 3 atendentes com número de requisições em atendimento para representar os times no desafio.
Existe um job rodando em cada consumidor que representa os atendentes finalizando suas requisições atuais. Ele roda a cada 40 segundos e é logado.
