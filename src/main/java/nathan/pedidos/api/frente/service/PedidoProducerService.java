package nathan.pedidos.api.frente.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import nathan.pedidos.api.frente.model.*;

import java.util.UUID;

@Service
public class PedidoProducerService {

    //Usamos esse Logger para promover o registro das mensagens e operações no arquivo envios.txt
    private static final Logger log = LoggerFactory.getLogger(PedidoProducerService.class);

    private static final String topico = "pedidos-criados";

    private final KafkaTemplate<String, Pedido> kafkaTemplate;

    public PedidoProducerService(KafkaTemplate<String, Pedido> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarPedido(Pedido pedido) {
        // Usamos uma String como chave para garantir roteamento (se necessário depois)
        String chaveMessage = UUID.randomUUID().toString();

        // Envia para o tópico, com a chave e o objeto serializado
        kafkaTemplate.send(topico, chaveMessage, pedido);

        log.info("📦 Pedido enviado para o Kafka com sucesso: {} (ID: {}, Qtd: {}, Chave: {})", pedido.getDescricao(), pedido.getPedidoId(), pedido.getQuantidade(), chaveMessage);
    }

}
