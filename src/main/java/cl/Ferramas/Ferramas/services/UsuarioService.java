package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.dto.*;
import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.entity.Rol;
import cl.Ferramas.Ferramas.entity.Sucursal;
import cl.Ferramas.Ferramas.entity.Usuario;
import cl.Ferramas.Ferramas.exception.Controllerexception;
import cl.Ferramas.Ferramas.mapper.ClienteMapper;
import cl.Ferramas.Ferramas.repository.ComunRep;
import cl.Ferramas.Ferramas.repository.RolRep;
import cl.Ferramas.Ferramas.repository.SucursalRep;
import cl.Ferramas.Ferramas.repository.UsuarioRep;
import jakarta.persistence.EntityNotFoundException;
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

        Rol rol = rolRepository.findById(registroDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrada"));
        usuario.setRol(rol);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Seteamos el ID en el DTO
        registroDTO.setId(usuarioGuardado.getUsuarioId());
        registroDTO.setPassword(null); // Evita devolver el password

        return registroDTO;
    }

    // crear usuarios (bodeguero, vendedor, etc)
    @Transactional
    public RegistroUsuarioDTO registrarUsuario(RegistroUsuarioDTO registroDTO) {
        Usuario usuario = clienteMapper.usuarioregistrodtoToUsuario(registroDTO);
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Comuna comuna = comunaRepository.findById(registroDTO.getComuna())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        usuario.setComuna(comuna);

        Rol rol = rolRepository.findById(registroDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);

        Sucursal sucursal = sucursalRep.findById(registroDTO.getSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        usuario.setSucursal(sucursal);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Seteamos el ID en el DTO
        registroDTO.setId(usuarioGuardado.getUsuarioId());
        registroDTO.setPassword(null); // Evita devolver el password

        return registroDTO;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAllExceptRolId2();
        return usuarios.stream()
                .map(clienteMapper::usuarioToUsuarioDto)
                .collect(Collectors.toList());
    }

    public List<ClienteDTO> listarClientes() {
        List<Usuario> clientes = usuarioRepository.findByRolId(2L);
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
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Controllerexception("Usuario con ID " + id + " no encontrado"));

        if (clienteDTO.getNombre() != null) {
            usuario.setNombre(clienteDTO.getNombre());
        }
        if (clienteDTO.getApellidop() != null) {
            usuario.setApellidop(clienteDTO.getApellidop());
        }
        if (clienteDTO.getApellidom() != null) {
            usuario.setApellidom(clienteDTO.getApellidom());
        }
        if (clienteDTO.getEmail() != null) {
            usuario.setEmail(clienteDTO.getEmail());
        }
        if (clienteDTO.getTelefono() != null) {
            usuario.setTelefono(clienteDTO.getTelefono());
        }
        if (clienteDTO.getDireccion() != null) {
            usuario.setDireccion(clienteDTO.getDireccion());
        }
        if (clienteDTO.getComuna() != null) {
            Comuna comuna = comunaRepository.findById(Long.valueOf(clienteDTO.getComuna()))
                    .orElseThrow(() -> new EntityNotFoundException("Comuna no encontrada"));
            usuario.setComuna(comuna);
        }
        if (clienteDTO.getRol() != null) {
            Rol rol = rolRepository.findById(clienteDTO.getRol())
                    .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
            usuario.setRol(rol);
        }
        if (clienteDTO.getActivo() != null) {
            usuario.setActivo(clienteDTO.getActivo());
        }

        usuario = usuarioRepository.save(usuario);
        return clienteMapper.usuarioToClienteDto(usuario);
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

    public boolean login(String email, String password) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return passwordEncoder.matches(password, usuario.getPassword());
        }

        return false;
    }
}
