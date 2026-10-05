package nathan.pedidos.api.frente.service;

import nathan.pedidos.api.frente.model.PedidoStatusEvent;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class PedidoStatusService {

    private static final String topicoStatus = "pedidos-status";
    private static final Logger log = LoggerFactory.getLogger(PedidoStatusService.class);

    // A ideia é que essa função atue na parte de Consumidor das mensagens emitida caso o pedido tenha sucesso com o estoque
    @KafkaListener(topics = topicoStatus, groupId = "grupo-frente")
    public void processarStatusPedido(PedidoStatusEvent status) {
        log.info("📢 Feedback recebido do Estoque! Pedido ID: {} | Status: {} | Mensagem: {} | Horário: {}", status.getPedidoId().toString(), status.getStatus(), status.getMensagem(), status.getData());
    }

}
