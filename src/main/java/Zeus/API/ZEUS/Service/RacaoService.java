package Zeus.API.ZEUS.Service;

import Zeus.API.ZEUS.Dto.DadosAtualizacaoRacao;
import Zeus.API.ZEUS.Dto.DadosCadastrosRacao;
import Zeus.API.ZEUS.Dto.DadosListagemRacao;
import Zeus.API.ZEUS.Model.Racao;
import Zeus.API.ZEUS.Repository.RacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class RacaoService {

    @Autowired
    private RacaoRepository repository;

    public ResponseEntity cadastrarRacao(DadosCadastrosRacao dadosCadastros){
        var racao = new Racao(dadosCadastros);
        racao.setDataCompra(LocalDate.now());
        var cadastro = repository.save(racao);

        return ResponseEntity.ok().body(cadastro);
    }


    public Page<DadosListagemRacao> listarRacao(Pageable lista){
        return repository.findAllByAtivoTrue(lista).map(DadosListagemRacao::new);
    }

    public ResponseEntity atualizarRacao (DadosAtualizacaoRacao dadosAtualizacao){
        Racao racao = repository.findById(dadosAtualizacao.id()).get();



        if(dadosAtualizacao.nome() != null){
            racao.setNome(dadosAtualizacao.nome());
        }
        if(dadosAtualizacao.kgQuantidade() == 0){
            racao.setKgQuantidade(racao.getKgQuantidade());
        } else if (dadosAtualizacao.kgQuantidade() >0) {
            racao.setKgQuantidade(dadosAtualizacao.kgQuantidade());
        }


        if (dadosAtualizacao.dataCompra()== null) {
        racao.setDataCompra(LocalDate.now());
        }else{
            racao.setDataCompra(dadosAtualizacao.dataCompra());
        }

        if(dadosAtualizacao.valorPago() == 0) {
            racao.setValorPago(racao.getValorPago());
        } else if (dadosAtualizacao.valorPago() >0) {
            racao.setValorPago(dadosAtualizacao.valorPago());
        }
        repository.save(racao);
        return ResponseEntity.ok().body(racao);
    }

    public ResponseEntity excluirRacao(Long id){
        Racao racao = repository.getReferenceById(id);
        racao.excluir();
        return ResponseEntity.noContent().build();
    }
}
