package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.security.config.Elements.JWT;
@Service
public class TokenService {
    private final String issuer = "API racao";

    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private UserRepository userRepository;



    public String gerarToken(User user) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer(issuer).
                    withSubject(user.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao enviar o token!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw new RuntimeException(("Token inválido ou expirado!"));
        }
    }

    private Date dataExpiracao() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusHours(2);
        Date dataExpiracao = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return dataExpiracao;
        //        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
//        //plusHours(2) serve para definir o limite de hr
//        //toInstant para converter em objeto INSTANT
//        // dentro do toInstant passa o ZoneOffset.off () para definir o fuso horario
    }




}
