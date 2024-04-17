package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUser;
import Zeus.API.ZEUS.Dto.DadosListagemLogin;
import Zeus.API.ZEUS.Dto.DadosListagemRacao;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
    public UserDetails atualizarUser(DadosAtualizacaoUser dadosAtualizacaoUser){
        var user = userRepository.findById(dadosAtualizacaoUser.id()).get();
        if(dadosAtualizacaoUser.login() == null){
            user.setLogin(user.getLogin());
        }else{
            user.setLogin(dadosAtualizacaoUser.login());
        }
        if(dadosAtualizacaoUser.senha() != null){
            user.setSenha(dadosAtualizacaoUser.senha());
        }
        else {
            user.setSenha(user.getSenha());
        }
        userRepository.save(user);
        return userRepository.findByLogin(user.getLogin());
    }

//    public UserDetails getLogin (String login) throws UsernameNotFoundException{
//        return userRepository.findByLogin(login);
//    }
//    public String getUsernameFromToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserPrincipal) {
//            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//            return userPrincipal.getUsername();
//        }
//        return null;
//    }

}
