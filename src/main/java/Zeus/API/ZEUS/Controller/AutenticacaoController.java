package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.*;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Service.AutenticacaoService;
import Zeus.API.ZEUS.Service.TokenService;
import Zeus.API.ZEUS.infra.security.DadosTokenJwt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosCadastroLogin dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);
        System.out.println(dados.login());
        var tokenjwt = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwt(tokenjwt));
    }
    @GetMapping
    public UserDetails getLogin(HttpServletRequest request) {
        // Obter o nome de usuário (login) do token JWT presente no cabeçalho da requisição
        String tokenJWT = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenService.getSubject(tokenJWT);

        // Carregar os detalhes do usuário com base no nome de usuário
        UserDetails userDetails = autenticacaoService.loadUserByUsername(username);
        return userDetails;
    }
}
