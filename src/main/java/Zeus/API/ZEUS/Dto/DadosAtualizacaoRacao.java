package Zeus.API.ZEUS.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public record DadosAtualizacaoRacao(
        Long id,
        String nome,
        int kgQuantidade,
        int valorPago,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataCompra
) {
}
