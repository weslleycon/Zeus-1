package Zeus.API.ZEUS.Dto;

import Zeus.API.ZEUS.Model.Racao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DadosListagemRacao(
        Long id,
        @NotBlank
        String nome,
        @NotNull
        int kqQuantidade,
        @NotNull
        int valorPago,
        @NotNull
        LocalDate dataCompra
) {
        public DadosListagemRacao (Racao racao){
                this(   racao.getId(),
                        racao.getNome(),
                        racao.getKgQuantidade(),
                        racao.getValorPago(),
                        racao.getDataCompra());
        }
}
