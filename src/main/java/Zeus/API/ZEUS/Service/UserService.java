package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUser;
import Zeus.API.ZEUS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity atualizarUser(DadosAtualizacaoUser dadosAtualizacaoUser){
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
        return ResponseEntity.ok().build();
    }
}
