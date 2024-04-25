package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUser;
import Zeus.API.ZEUS.Dto.DadosCadastroLogin;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Repository.UserRepository;
import Zeus.API.ZEUS.Service.EsqueceuSenhaService;
import Zeus.API.ZEUS.Service.UserService;
import Zeus.API.ZEUS.infra.security.DadosTokenJwt;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:8081/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private EsqueceuSenhaService esqueceuSenhaService;

    @PutMapping
    @Transactional
    public ResponseEntity atualizarUser(@RequestBody DadosAtualizacaoUser dadosAtualizacaoUser) {
        ResponseEntity response = userService.atualizarUser(dadosAtualizacaoUser);
        return ResponseEntity.ok(response.getBody());
    }


    @PostMapping
    @Transactional
    public ResponseEntity<String> esqueceuSenha(@RequestBody User usuario) {
        boolean senhaRedefinida = esqueceuSenhaService.redefinirSenha(usuario.getLogin(), usuario.getSenha());
        if (senhaRedefinida) {
            return ResponseEntity.status(HttpStatus.OK).body("Senha redefinida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

}

