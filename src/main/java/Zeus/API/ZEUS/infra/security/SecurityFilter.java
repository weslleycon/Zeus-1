package Zeus.API.ZEUS.infra.security;

import Zeus.API.ZEUS.Repository.UserRepository;
import Zeus.API.ZEUS.Repository.UsuarioRepository;
import Zeus.API.ZEUS.Service.TokenService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var tokenJWT =recuperarToken(request);
            if (tokenJWT != null) {
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = userRepository.findByLogin(subject);
                System.out.println(usuario.getUsername());
                System.out.println(tokenJWT);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

}

