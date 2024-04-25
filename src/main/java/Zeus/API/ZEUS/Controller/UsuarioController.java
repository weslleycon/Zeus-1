package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.*;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Service.AutenticacaoService;
import Zeus.API.ZEUS.Service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "http://localhost:8081/")
public class UsuarioController {
@Autowired
private UsuarioService usuarioService;

@Autowired
private AutenticacaoService autenticacaoService;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario){
        return usuarioService.cadastrarUsuario(dadosCadastroUsuario);
    }

        //    @GetMapping
        //    public Page<DadosListagemUsuario> listarUsuario (@PageableDefault(size = 30, sort = {"nome"}) Pageable lista){
        //        return usuarioService.listarUsuario(lista);
        //    }


//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizarUsuario(@RequestBody @Valid DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
//        return usuarioService.atualizarUsuario(dadosAtualizacaoUsuario);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario (@PathVariable Long id){
        return usuarioService.excluirUsuario(id);
    }


    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        List<User> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);

    }
    @PostMapping("/atualizar")
    @Transactional
    public ResponseEntity<?> atualizarUsuario(@RequestBody DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        // Obtém o nome de usuário (login) a partir do contexto de segurança
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Chama o serviço de usuário para atualizar os dados do usuário
        ResponseEntity<?> response = usuarioService.atualizarUsuario(username, dadosAtualizacaoUsuario);

        return response;
    }
//    @PatchMapping("/atualizar")
//    public ResponseEntity<User> atualizarUsuario(@RequestBody DadosAtualizacaoUser dadosAtualizacaoUser) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = userDetails.getUsername();
//        ResponseEntity<?> response = usuarioService.atualizarUsuario(username, dadosAtualizacaoUser);
//        return response;
//    }
}
