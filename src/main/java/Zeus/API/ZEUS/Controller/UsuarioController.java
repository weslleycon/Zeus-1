package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUsuario;
import Zeus.API.ZEUS.Dto.DadosCadastroUsuario;
import Zeus.API.ZEUS.Dto.DadosListagemRacao;
import Zeus.API.ZEUS.Dto.DadosListagemUsuario;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "http://localhost:8081/")
public class UsuarioController {
@Autowired
private UsuarioService usuarioService;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario){
        return usuarioService.cadastrarUsuario(dadosCadastroUsuario);
    }

        //    @GetMapping
        //    public Page<DadosListagemUsuario> listarUsuario (@PageableDefault(size = 30, sort = {"nome"}) Pageable lista){
        //        return usuarioService.listarUsuario(lista);
        //    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        return usuarioService.atualizarUsuario(dadosAtualizacaoUsuario);
    }

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

}
