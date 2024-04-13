package Zeus.API.ZEUS.Dto;

import Zeus.API.ZEUS.Model.User;

public record DadosListagemLogin(
        String login
) {
    public DadosListagemLogin (User user){
        this(user.getUsername());
    }

}
