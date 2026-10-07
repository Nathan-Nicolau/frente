package nathan.pedidos.api.frente.service;

import nathan.pedidos.api.frente.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducerService {

    //Usamos esse Logger para promover o registro das mensagens e operações no arquivo envios.txt
    private static final Logger log = LoggerFactory.getLogger(PedidoProducerService.class);

    private static final String topicoCriacao = "pedidos-criados";

    private final KafkaTemplate<String, Pedido> kafkaTemplate;

    public PedidoProducerService(KafkaTemplate<String, Pedido> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarPedido(Pedido pedido) {
        // Usamos o pedidoId como chave para garantir que todas as mensagens relacionadas a ele sejam
        // agrupadas de forma sequencial e na mesma partição
        String chavePedido = String.valueOf(pedido.getPedidoId());

        // Envia para o tópico, com a chave e o objeto serializado
        kafkaTemplate.send(topicoCriacao, chavePedido, pedido);

        log.info("📦 Pedido enviado para o Kafka com sucesso: {} (ID: {}, Quantidade: {}, Chave: {})", pedido.getDescricao(), pedido.getPedidoId(), pedido.getQuantidade(), chavePedido);
    }

}
