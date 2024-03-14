package Zeus.API.ZEUS.Repository;

import Zeus.API.ZEUS.Dto.DadosListagemRacao;
import Zeus.API.ZEUS.Model.Racao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacaoRepository extends JpaRepository<Racao, Long> {
    Page<Racao> findAllByAtivoTrue (Pageable lista);

}
