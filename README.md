# Frente

Essa API desenvolvida com Java 17 + Spring Boot, serve como lado de Producer (Produtor) de mensagens para o Broker Kafka  
configurado localmente. A funcionalidade base é enviar uma requisição de pedido, para que o respectivo produto seja descontado  
do estque (Banco de dados H2 em memória) através da mensagem enviada pelo Kafka, contornando um envio direto de requisição entre APIs