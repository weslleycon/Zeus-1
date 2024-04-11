package Zeus.API.ZEUS.Dto;

import Zeus.API.ZEUS.Model.Usuario;

public record DadosListagemUsuario(
         Long id,
         String nome,
         String email,
         String telefone,
         String cpf,
         int idade


) {
    public DadosListagemUsuario (Usuario usuario){
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getIdade());
    }
}
