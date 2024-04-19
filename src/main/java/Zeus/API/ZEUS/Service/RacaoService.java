    package Zeus.API.ZEUS.Service;

    import Zeus.API.ZEUS.Dto.DadosAtualizacaoRacao;
    import Zeus.API.ZEUS.Dto.DadosCadastrosRacao;
    import Zeus.API.ZEUS.Dto.DadosListagemRacao;
    import Zeus.API.ZEUS.Model.Racao;
    import Zeus.API.ZEUS.Model.User;
    import Zeus.API.ZEUS.Model.Usuario;
    import Zeus.API.ZEUS.Repository.RacaoRepository;
    import Zeus.API.ZEUS.Repository.UserRepository;
    import Zeus.API.ZEUS.Repository.UsuarioRepository;
    import Zeus.API.ZEUS.infra.Exception.ValidacaoException;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.time.LocalDate;
    import java.util.Optional;


    @Service
    public class RacaoService {

        @Autowired
        private RacaoRepository repository;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private TokenService tokenService;
        @Autowired
        private AutenticacaoService autenticacaoService;
        @Autowired
        private UserRepository userRepository;

    //    public ResponseEntity cadastrarRacao(DadosCadastrosRacao dadosCadastros){
    //        Racao racao = new Racao(dadosCadastros);
    //        repository.save(racao);
    //        return ResponseEntity.ok().build();
    //
    //
    //    }

            public ResponseEntity cadastrarRacao(DadosCadastrosRacao dadosCadastros, Long idUsuario){
                Usuario usuario = usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                Racao racao = new Racao(dadosCadastros);
                racao.setDataCompra(LocalDate.now());
                racao.setUsuario(usuario); // Associando a ração ao usuário
                repository.save(racao);
                return ResponseEntity.ok().build();
            }


        public Page<DadosListagemRacao> listarRacao(HttpServletRequest request, Pageable lista) {
            String tokenJWT = request.getHeader("Authorization").replace("Bearer ", "");
            String username = tokenService.getSubject(tokenJWT);
            User user = userRepository.findByLogin(username);
            Long usuarioId = user.getId();

            return repository.listarRacaoPorUser(usuarioId, lista).map(DadosListagemRacao::new);
        }

        public ResponseEntity atualizarRacao (DadosAtualizacaoRacao dadosAtualizacao){
            Racao racao = repository.findById(dadosAtualizacao.id()).get();

       if(dadosAtualizacao.nome() ==null){
           racao.setNome(racao.getNome());
       }else{
           racao.setNome(dadosAtualizacao.nome());
       }

            switch (dadosAtualizacao.kgQuantidade()) {
                case 0:
                    racao.setKgQuantidade(racao.getKgQuantidade());
                    break;
                default:
                    racao.setKgQuantidade(dadosAtualizacao.kgQuantidade());
            }



            if (dadosAtualizacao.dataCompra() == null) {
                racao.setDataCompra(LocalDate.now());
            }
            if(dadosAtualizacao.dataCompra().isAfter(LocalDate.now())){
                throw new RuntimeException("Verique a sua data");
            }
            else {
                racao.setDataCompra(dadosAtualizacao.dataCompra());
            }

            if (!dadosAtualizacao.valorPago().equals(BigDecimal.ZERO)) {
                racao.setValorPago(racao.getValorPago());
            } else {
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
