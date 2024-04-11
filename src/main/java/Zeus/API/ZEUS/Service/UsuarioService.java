package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUsuario;
import Zeus.API.ZEUS.Dto.DadosCadastroUsuario;
import Zeus.API.ZEUS.Dto.DadosListagemUsuario;
import Zeus.API.ZEUS.Model.Usuario;
import Zeus.API.ZEUS.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

public ResponseEntity cadastrarUsuario(DadosCadastroUsuario dadosCadastroUsuario){
    var usuario = new Usuario(dadosCadastroUsuario);
    var cadastrar = usuarioRepository.save(usuario);
    return ResponseEntity.ok().body(cadastrar);
}
public Page<DadosListagemUsuario> listarUsuario (Pageable lista){
    return usuarioRepository.findAllByAtivoTrue(lista).map(DadosListagemUsuario::new);
}
public ResponseEntity atualizarUsuario(DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
    var usuario = usuarioRepository.findById(dadosAtualizacaoUsuario.id()).get();

    if(dadosAtualizacaoUsuario.nome() != null){
        usuario.setNome(dadosAtualizacaoUsuario.nome());
    }
    if(dadosAtualizacaoUsuario.cpf() != null){
        usuario.setCpf(dadosAtualizacaoUsuario.cpf());
    }
    if(dadosAtualizacaoUsuario.email() != null){
        usuario.setEmail(dadosAtualizacaoUsuario.email());
    }
    if(dadosAtualizacaoUsuario.idade() >0){
        usuario.setIdade(dadosAtualizacaoUsuario.idade());
    }
    usuarioRepository.save(usuario);
    return ResponseEntity.ok().body(usuario);
}
public ResponseEntity excluirUsuario (Long id){
Usuario usuario = usuarioRepository.getReferenceById(id);
    usuario.excluir();
    return ResponseEntity.noContent().build();
}
}
