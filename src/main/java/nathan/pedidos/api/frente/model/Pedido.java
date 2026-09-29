package nathan.pedidos.api.frente.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Pedido implements Serializable {

    private Long pedidoId;
    private Long produtoId;
    private String descricao;
    private BigDecimal valor;
    private int quantidade;


}
