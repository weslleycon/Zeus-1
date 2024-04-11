package Zeus.API.ZEUS.Dto;

import Zeus.API.ZEUS.Model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroLogin(
        @NotBlank
        String login,
        @NotBlank
        String senha


) {
}
