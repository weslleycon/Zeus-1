package Zeus.API.ZEUS.Repository;

import Zeus.API.ZEUS.Model.Racao;
import Zeus.API.ZEUS.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAllByAtivoTrue (Pageable lista);
}
