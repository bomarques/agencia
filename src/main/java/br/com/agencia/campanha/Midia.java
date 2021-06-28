package br.com.agencia.campanha;

import br.com.agencia.cliente.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Campanha campanha;
    private TipoDeMidia tipoDeMidia;
    private String periodo;
    private Status status;
    private BigDecimal valorDedicado;
    private Long engajamentoEstimado;
    private Long engajamentoAlcancado;
    private Long alcance;
    @OneToOne
    private Veiculo veiculo;

}
