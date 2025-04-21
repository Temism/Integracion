package cl.Ferramas.Ferramas.services;





import cl.Ferramas.Ferramas.entity.Usuario;

import cl.Ferramas.Ferramas.repository.UsuarioRep;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;




import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRep user;



    public List<Usuario> ListarUsuarios(){
        return user.findAll();
    }

    public Optional<Usuario> BuscarUsuarioPorId(Long usuarioId){
        return user.findById(usuarioId);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return user.save(usuario);
    }

    public void EliminarUusuario(Long usuarioId) {
        user.deleteById(usuarioId);
    }
}