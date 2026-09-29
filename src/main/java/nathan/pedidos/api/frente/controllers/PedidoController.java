package nathan.pedidos.api.frente.controllers;

import nathan.pedidos.api.frente.service.PedidoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nathan.pedidos.api.frente.model.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoProducerService pedidoProducerService;

    @Autowired
    public PedidoController(PedidoProducerService pedidoProducerService) {
        this.pedidoProducerService = pedidoProducerService;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
        // Simulação simples: caso o ID não venha preenchido, preenchemos
        if (pedido.getPedidoId() == null) {
            pedido.setPedidoId(System.currentTimeMillis());
        }

        // Chama o serviço que publica no Kafka
        pedidoProducerService.enviarPedido(pedido);

        // Responde ao cliente IMEDIATAMENTE (desacoplamento)
        return ResponseEntity.ok("Pedido registrado com sucesso e na fila de processamento!");
    }

}
