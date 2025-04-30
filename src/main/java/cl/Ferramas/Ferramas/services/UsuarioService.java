package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.dto.ClienteDTO;
import cl.Ferramas.Ferramas.dto.RegistroClienteDTO;
import cl.Ferramas.Ferramas.dto.RegistroUsuarioDTO;
import cl.Ferramas.Ferramas.dto.UsuarioDTO;
import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.entity.Rol;
import cl.Ferramas.Ferramas.entity.Sucursal;
import cl.Ferramas.Ferramas.entity.Usuario;

import cl.Ferramas.Ferramas.mapper.ClienteMapper;
import cl.Ferramas.Ferramas.repository.ComunRep;
import cl.Ferramas.Ferramas.repository.RolRep;
import cl.Ferramas.Ferramas.repository.SucursalRep;
import cl.Ferramas.Ferramas.repository.UsuarioRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRep usuarioRepository;

    @Autowired
    private RolRep rolRepository;

    @Autowired
    private ComunRep comunaRepository;

    @Autowired
    private SucursalRep sucursalRep;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // crear clientes
    @Transactional
    public RegistroClienteDTO registrarCliente(RegistroClienteDTO registroDTO) {


        Usuario usuario = clienteMapper.clienteRegistroDtoToUsuario(registroDTO);

        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Comuna comuna = comunaRepository.findById(registroDTO.getComuna())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));

        usuario.setComuna(comuna);

        Rol rol = rolRepository.findById(registroDTO.getRol()).orElseThrow(() -> new RuntimeException("Rol no encontrada"));
        usuario.setRol(rol);

        usuario = usuarioRepository.save(usuario);

        return clienteMapper.usuarioToClienteResponseDto(usuario);
    }

    // crear usuarios (bodeguero, vendedor, etc)
    @Transactional
    public RegistroUsuarioDTO registrarUsuario(RegistroUsuarioDTO registroDTO) {


        Usuario usuario = clienteMapper.usuarioregistrodtoToUsuario(registroDTO);

        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Comuna comuna = comunaRepository.findById(registroDTO.getComuna())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));

        usuario.setComuna(comuna);

        Rol rol = rolRepository.findById(registroDTO.getRol()).orElseThrow(() -> new RuntimeException("Rol no encontrada"));
        usuario.setRol(rol);

        Sucursal sucursal = sucursalRep.findById(registroDTO.getSucursal()).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        usuario.setSucursal(sucursal);

        usuario = usuarioRepository.save(usuario);

        return clienteMapper.usuarioToUsuarioresponsedto(usuario);
    }


    // lista de usuarios(bodegueros,vendedores,etc)

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAllExceptRolId2();
        return usuarios.stream()
                .map(clienteMapper::usuarioToUsuarioDto)
                .collect(Collectors.toList());
    }


    // lista solo de clientes
    public List<ClienteDTO> listarClientes() {
        List<Usuario> clientes = usuarioRepository.findByRolId(2L); // Ajusta el ID para que coincida con cliente
        return clientes.stream()
                .map(clienteMapper::usuarioToClienteDto)
                .collect(Collectors.toList());
    }


    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.map(clienteMapper::usuarioToUsuarioDto).orElse(null);
    }


    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        return usuarioOptional.map(clienteMapper::usuarioToUsuarioDto).orElse(null);
    }




    @Transactional
    public UsuarioDTO actualizarUsuario(Long id, RegistroUsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Actualizar los campos básicos
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellidop(usuarioDTO.getApellidop());
            usuario.setApellidom(usuarioDTO.getApellidom());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuario.setDireccion(usuarioDTO.getDireccion());

            // Si se entrega una nueva contraseña, actualizarla
            if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
            }

            // Actualizar comuna si se entrega
            if (usuarioDTO.getComuna() != null) {
                Comuna comuna = comunaRepository.findById(usuarioDTO.getComuna())
                        .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
                usuario.setComuna(comuna);
            }

            // Actualizar rol si se entrega
            if (usuarioDTO.getRol() != null) {
                Rol rol = rolRepository.findById(usuarioDTO.getRol())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
                usuario.setRol(rol);
            }

            // Actualizar sucursal si se entrega
            if (usuarioDTO.getSucursal() != null) {
                Sucursal sucursal = sucursalRep.findById(usuarioDTO.getSucursal())
                        .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
                usuario.setSucursal(sucursal);
            }

            usuario = usuarioRepository.save(usuario);
            return clienteMapper.usuarioToUsuarioDto(usuario);
        }

        return null;
    }


    @Transactional
    public ClienteDTO actualizarCliente(Long id, RegistroClienteDTO clienteDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Actualizar los campos básicos
            usuario.setNombre(clienteDTO.getNombre());
            usuario.setApellidom(clienteDTO.getApellidom());
            usuario.setApellidop(clienteDTO.getApellidop());
            usuario.setEmail(clienteDTO.getEmail());
            usuario.setTelefono(clienteDTO.getTelefono());
            usuario.setDireccion(clienteDTO.getDireccion());

            // Si se entrega una nueva contraseña, actualizarla
            if (clienteDTO.getPassword() != null && !clienteDTO.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
            }

            // Actualizar comuna si se entrega
            if (clienteDTO.getComuna() != null) {
                Comuna comuna = comunaRepository.findById(clienteDTO.getComuna())
                        .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
                usuario.setComuna(comuna);
            }

            // Actualizar rol si se entrega
            if (clienteDTO.getRol() != null) {
                Rol rol = rolRepository.findById(clienteDTO.getRol())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
                usuario.setRol(rol);
            }

            usuario = usuarioRepository.save(usuario);
            return clienteMapper.usuarioToClienteDto(usuario);
        }

        return null;
    }


    @Transactional
    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Transactional
    public boolean cambiarPassword(Long id, String passwordActual, String passwordNueva) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Verificar que la contraseña actual sea correcta
            if (passwordEncoder.matches(passwordActual, usuario.getPassword())) {
                usuario.setPassword(passwordEncoder.encode(passwordNueva));
                usuarioRepository.save(usuario);
                return true;
            }
        }

        return false;
    }


    public List<UsuarioDTO> listarUsuariosPorSucursal(Long sucursalId) {
        List<Usuario> usuarios = usuarioRepository.findBySucursalId(sucursalId);
        return usuarios.stream()
                .map(clienteMapper::usuarioToUsuarioDto)
                .collect(Collectors.toList());
    }


    public List<UsuarioDTO> listarUsuariosPorRol(Long rolId) {
        List<Usuario> usuarios = usuarioRepository.findByRolId(rolId);
        return usuarios.stream()
                .map(clienteMapper::usuarioToUsuarioDto)
                .collect(Collectors.toList());



    }
}