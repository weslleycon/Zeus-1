package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUser;
import Zeus.API.ZEUS.Model.User;
import Zeus.API.ZEUS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Base64Util.encode;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity atualizarUser(DadosAtualizacaoUser dadosAtualizacaoUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByLogin(username);

        // Verifique se o ID que está sendo atualizado é o mesmo que o ID do usuário autenticado
        if (!dadosAtualizacaoUser.id().equals(user.getId())) {
            // Se não for o mesmo, retorne uma resposta de erro
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você só pode alterar seus próprios dados.");
        }

        // Atualize os dados do usuário
        if (dadosAtualizacaoUser.login() != null) {
            user.setLogin(dadosAtualizacaoUser.login());
        }
        if (dadosAtualizacaoUser.senha() != null) {
            user.setSenha(dadosAtualizacaoUser.senha());
        }

        // Salve as alterações no usuário
        userRepository.save(user);

        // Retorne uma resposta de sucesso
        return ResponseEntity.ok().build();
    }
}


//
//// Obtenha o ID do usuário autenticado
//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//String username = authentication.getName();
//
//// Busque o usuário a ser atualizado
//User user = userRepository.findByLogin(username);
//
//// Verifique se o ID que está sendo atualizado é o mesmo que o ID do usuário autenticado
//        if (!dadosAtualizacaoUser.id().equals(user.getId())) {
//        // Se não for o mesmo, retorne uma resposta de erro
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você só pode alterar seus próprios dados.");
//        }
//
//                // Atualize os dados do usuário
//                if (dadosAtualizacaoUser.login() != null) {
//        user.setLogin(dadosAtualizacaoUser.login());
//        }
//        if (dadosAtualizacaoUser.senha() != null) {
//// Criptografe a nova senha antes de salvá-la
//String senhaCriptografada = new BCryptPasswordEncoder().encode(dadosAtualizacaoUser.senha());
//            user.setSenha(senhaCriptografada);
//        }
//
//                // Salve as alterações no usuário
//                userRepository.save(user);
//
//// Retorne uma resposta de sucesso
//        return ResponseEntity.ok().build();
//    }
//            }