package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoUser;
import Zeus.API.ZEUS.Repository.UserRepository;
import Zeus.API.ZEUS.Service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:8081/")
public class UserController {

    @Autowired
    private UserService userService;
    @PutMapping
    @Transactional
    public ResponseEntity atualizarUser(@RequestBody DadosAtualizacaoUser dadosAtualizacaoUser) {
        ResponseEntity response = userService.atualizarUser(dadosAtualizacaoUser);
        return ResponseEntity.ok().build();
    }

}
