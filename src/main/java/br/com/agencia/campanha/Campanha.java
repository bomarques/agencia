package br.com.agencia.campanha;

import br.com.agencia.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    private String titulo;
    private String objetivo;
    private String publicoAlvo;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal orcamento;
    private Status status;
}
