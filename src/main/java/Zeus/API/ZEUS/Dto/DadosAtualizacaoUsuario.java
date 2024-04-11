package Zeus.API.ZEUS.Dto;

public record DadosAtualizacaoUsuario(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        int idade
) {
}
