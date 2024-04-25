package Zeus.API.ZEUS.Service;
import Zeus.API.ZEUS.Dto.DadosAtualizacaoUser;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
    public UserDetails atualizarUser(DadosAtualizacaoUser dadosAtualizacaoUser) {
        Optional<User> optionalUser = userRepository.findById(dadosAtualizacaoUser.id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Verifica se o novo login foi passado e atualiza, se necessário
            if (dadosAtualizacaoUser.login() != null) {
                user.setLogin(dadosAtualizacaoUser.login());
            }else {
                user.setLogin(user.getLogin());
            }
            // Verifica se a nova senha foi passada e atualiza, se necessário
            if (dadosAtualizacaoUser.senha() != null) {
                // Criptografa a nova senha antes de atualizar
                user.setSenha(criptografarSenha(dadosAtualizacaoUser.senha()));
            }
            userRepository.save(user);
            return userRepository.findByLogin(user.getLogin());
        } else {
            throw new RuntimeException("Usuário não encontrado para atualização.");
        }
    }


    private String criptografarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
    public Long getIdUsuarioPorLogin(String username) {
        User user = userRepository.findByLogin(username);
        if (user != null) {
            return user.getId();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado para o login: " + username);
        }
    }
}



