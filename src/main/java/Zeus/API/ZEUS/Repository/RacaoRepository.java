package Zeus.API.ZEUS.Repository;

import Zeus.API.ZEUS.Model.Racao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaoRepository extends JpaRepository<Racao, Long> {

    @Query("SELECT r FROM Racao r WHERE r.usuario.id = :usuarioId AND r.ativo = true")
    Page <Racao> listarRacaoPorUser (Long usuarioId, Pageable lista);

}
