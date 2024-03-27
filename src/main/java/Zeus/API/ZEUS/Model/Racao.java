package Zeus.API.ZEUS.Model;

import Zeus.API.ZEUS.Dto.DadosCadastrosRacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "racao")
@Getter
@Setter
public class Racao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int kgQuantidade;
    private double valorPago;
    private LocalDate DataCompra;
    private boolean ativo;

    public Racao (DadosCadastrosRacao dadosCadastro){
        this.nome = dadosCadastro.nome();
        this.kgQuantidade = dadosCadastro.kgQuantidade();
        this.valorPago = dadosCadastro.valorPago();
        this.DataCompra = dadosCadastro.dataCompra();
        this.ativo = true;
    }

    public void excluir(){
        this.ativo = false;
    }
    public Racao(){

    }

}
