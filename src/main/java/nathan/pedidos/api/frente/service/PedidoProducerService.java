package nathan.pedidos.api.frente.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import nathan.pedidos.api.frente.model.*;

import java.util.UUID;

@Service
public class PedidoProducerService {

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

        System.out.println("📦 Pedido enviado para o Kafka: " + pedido.getDescricao());
    }

}
