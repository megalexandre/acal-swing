package br.org.acal.resources.legacy.dao;

import br.org.acal.resources.model.UserModel;

public interface UsuarioService {

    void AdicionarUsuario(UserModel usuario);
    void AlterarUsuario(UserModel usuario);
    void ApagarUsuario(UserModel usuario);
    
    
}
