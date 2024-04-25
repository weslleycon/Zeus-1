package Zeus.API.ZEUS.Dto;

import Zeus.API.ZEUS.Model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario (

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        int idade,
        @Valid
        DadosCadastroLogin user
){
}
