package Zeus.API.ZEUS.Controller;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoRacao;
import Zeus.API.ZEUS.Dto.DadosCadastrosRacao;
import Zeus.API.ZEUS.Dto.DadosListagemRacao;
import Zeus.API.ZEUS.Service.RacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("racao")
@CrossOrigin(origins = "http://localhost:8081/")
public class RacaoController {

    @Autowired
    private RacaoService racaoService;

@Transactional
@PostMapping
    public ResponseEntity cadastrarRacao(@RequestBody @Valid DadosCadastrosRacao dados){
        return racaoService.cadastrarRacao(dados);
    }

    @GetMapping
    public Page<DadosListagemRacao> listarRacao (@PageableDefault(size = 30, sort = {"nome"}) Pageable lista){
    return racaoService.listarRacao(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarRacao(@RequestBody @Valid DadosAtualizacaoRacao dadosAtualizacao){
    return racaoService.atualizarRacao(dadosAtualizacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirRacao (@PathVariable Long id){
        return racaoService.excluirRacao(id);
    }
}
