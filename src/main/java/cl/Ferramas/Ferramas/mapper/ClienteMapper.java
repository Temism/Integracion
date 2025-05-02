package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.ClienteDTO;

import cl.Ferramas.Ferramas.dto.RegistroClienteDTO;
import cl.Ferramas.Ferramas.dto.RegistroUsuarioDTO;
import cl.Ferramas.Ferramas.dto.UsuarioDTO;
import cl.Ferramas.Ferramas.entity.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClienteMapper {

    // registro cliente
    public Usuario clienteRegistroDtoToUsuario(RegistroClienteDTO registroDTO) {
        if (registroDTO == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setApellidop(registroDTO.getApellidop());
        usuario.setApellidom(registroDTO.getApellidom());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setDireccion(registroDTO.getDireccion());
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setFechaNacimiento(registroDTO.getFechaNacimiento());
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setRut(registroDTO.getRut());
        usuario.setActivo(true);

        return usuario;
    }

    public RegistroClienteDTO usuarioToClienteResponseDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        RegistroClienteDTO responseDTO = new RegistroClienteDTO();
        responseDTO.setNombre(usuario.getNombre());
        responseDTO.setApellidop(usuario.getApellidop());
        responseDTO.setApellidom(usuario.getApellidom());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setDireccion(usuario.getDireccion());
        responseDTO.setTelefono(usuario.getTelefono());
        responseDTO.setFechaRegistro(usuario.getFechaRegistro());


        return responseDTO;
    }


    // registro usuarios(bodeguero,vendedor,etc)

    public Usuario usuarioregistrodtoToUsuario(RegistroUsuarioDTO registroDTO) {
        if (registroDTO == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setApellidop(registroDTO.getApellidop());
        usuario.setApellidom(registroDTO.getApellidom());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setDireccion(registroDTO.getDireccion());
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setFechaNacimiento(registroDTO.getFechaNacimiento());
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setRut(registroDTO.getRut());
        usuario.setActivo(true);

        return usuario;
    }

    public RegistroUsuarioDTO usuarioToUsuarioresponsedto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        RegistroUsuarioDTO responseDTO = new RegistroUsuarioDTO();
        responseDTO.setNombre(usuario.getNombre());
        responseDTO.setApellidop(usuario.getApellidop());
        responseDTO.setApellidom(usuario.getApellidom());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setDireccion(usuario.getDireccion());
        responseDTO.setTelefono(usuario.getTelefono());
        responseDTO.setFechaRegistro(usuario.getFechaRegistro());


        return responseDTO;
    }


    // cliente a usuario
    public ClienteDTO usuarioToClienteDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(usuario.getNombre());
        clienteDTO.setApellidop(usuario.getApellidop());
        clienteDTO.setApellidom(usuario.getApellidom());
        clienteDTO.setEmail(usuario.getEmail());
        clienteDTO.setDireccion(usuario.getDireccion());
        clienteDTO.setTelefono(usuario.getTelefono());
        clienteDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        clienteDTO.setFechaRegistro(usuario.getFechaRegistro());
        clienteDTO.setRut(usuario.getRut());


        return clienteDTO;
    }


    public UsuarioDTO usuarioToUsuarioDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId(usuario.getUsuarioId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellidop(usuario.getApellidop());
        usuarioDTO.setApellidom(usuario.getApellidom());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setDireccion(usuario.getDireccion());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDTO.setFechaRegistro(usuario.getFechaRegistro());
        usuarioDTO.setRut(usuario.getRut());
        usuarioDTO.setUltimoLogin(usuario.getUltimoLogin());

        usuarioDTO.setComuna(usuario.getComuna() != null ? usuario.getComuna().getNombre() : null);
        usuarioDTO.setSucursal(usuario.getSucursal() != null ? usuario.getSucursal().getNombre() : null);
        usuarioDTO.setRol(usuario.getRol() != null ? usuario.getRol().getNombre() : null);

        return usuarioDTO;
    }
}
