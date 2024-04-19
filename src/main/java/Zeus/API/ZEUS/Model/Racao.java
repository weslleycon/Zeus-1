package Zeus.API.ZEUS.Model;

import Zeus.API.ZEUS.Dto.DadosCadastrosRacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "racao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Racao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int kgQuantidade;
    private BigDecimal valorPago;
    private LocalDate DataCompra;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Racao (DadosCadastrosRacao dadosCadastro){
        this.nome = dadosCadastro.nome();
        this.kgQuantidade = dadosCadastro.kgQuantidade();
        this.valorPago = dadosCadastro.valorPago();
        this.DataCompra = dadosCadastro.dataCompra();
        this.ativo = true;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void excluir(){
        this.ativo = false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getKgQuantidade() {
        return kgQuantidade;
    }

    public void setKgQuantidade(int kgQuantidade) {
        this.kgQuantidade = kgQuantidade;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataCompra() {
        return DataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        DataCompra = dataCompra;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
