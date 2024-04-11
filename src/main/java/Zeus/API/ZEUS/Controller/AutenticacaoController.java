package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.DadosAutenciacaoUser;
import Zeus.API.ZEUS.Dto.DadosCadastroLogin;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Service.TokenService;
import Zeus.API.ZEUS.infra.security.DadosTokenJwt;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController  {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin( @RequestBody @Valid DadosCadastroLogin dados){
    var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
    var authentication = manager.authenticate(token);
        System.out.println(dados.login());
    var tokenjwt = tokenService.gerarToken((User) authentication.getPrincipal());
    return ResponseEntity.ok(new DadosTokenJwt(tokenjwt));
    }
}
