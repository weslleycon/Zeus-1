package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUsuario;
import Zeus.API.ZEUS.Dto.DadosCadastroUsuario;
import Zeus.API.ZEUS.Dto.DadosListagemUsuario;
import Zeus.API.ZEUS.Model.Racao;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Model.Usuario;
import Zeus.API.ZEUS.Repository.RacaoRepository;
import Zeus.API.ZEUS.Repository.UserRepository;
import Zeus.API.ZEUS.Repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RacaoRepository racaoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutenticacaoService autenticacaoService;

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

    if(dadosAtualizacaoUsuario.email() != null){
        usuario.setEmail(dadosAtualizacaoUsuario.email());
    }
    if(dadosAtualizacaoUsuario.idade() >0){
        usuario.setIdade(dadosAtualizacaoUsuario.idade());
    }
    usuarioRepository.save(usuario);
    return ResponseEntity.ok().body(usuario);
}
    public ResponseEntity<?> atualizarUsuario(String username, DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        // Salva o usuário atualizado no banco de dados
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
public ResponseEntity excluirUsuario (Long id){
Usuario usuario = usuarioRepository.getReferenceById(id);
    usuario.excluir();
    return ResponseEntity.noContent().build();
}
    public List<User> listarUsuarios() {
        return userRepository.findAll(); // Supondo que você tenha um método findAll() no seu UserRepository para buscar todos os usuários
    }
}
