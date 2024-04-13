package Zeus.API.ZEUS.Model;

import Zeus.API.ZEUS.Dto.DadosCadastroLogin;
import Zeus.API.ZEUS.Dto.DadosCadastroUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;
        private boolean ativo;
        private String nome;
        private String email;
        private String telefone;
        private String cpf;
        private int idade;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_login")
        private User usuario;




        public Usuario (DadosCadastroUsuario dadosCadastroUsuario){
            this.ativo = true;
            this.cpf = dadosCadastroUsuario.cpf();
            this.email = dadosCadastroUsuario.email();
            this.nome = dadosCadastroUsuario.nome();
            this.idade = dadosCadastroUsuario.idade();
            this.telefone = dadosCadastroUsuario.telefone();
            this.usuario = new User(dadosCadastroUsuario.user());
        }

        public void excluir (){
            this.ativo = false;
        }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
