package cl.Ferramas.Ferramas.controller;




import cl.Ferramas.Ferramas.dto.*;
import cl.Ferramas.Ferramas.entity.Usuario;
import cl.Ferramas.Ferramas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService clienteService;

    @PostMapping
    public ResponseEntity<RegistroUsuarioDTO> registrarCliente(@Valid @RequestBody RegistroUsuarioDTO registroDTO) {
        RegistroUsuarioDTO usuarioregistrado = clienteService.registrarUsuario(registroDTO);
        return new ResponseEntity<>(usuarioregistrado, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> usuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuario = clienteService.buscarUsuarioPorId(id);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping
    public List<UsuarioDTO> getAll() {
        return clienteService.listarUsuarios();
    }

    @GetMapping("/usuarioporsucursal/{id}")
    public List<UsuarioDTO> usuariosporsucursal(@PathVariable Long id){
        return clienteService.listarUsuariosPorSucursal(id);

    }

    @GetMapping("/usuarioporrol/{id}")
    public List<UsuarioDTO> usuarioPorRol(@PathVariable Long id){
        return clienteService.listarUsuariosPorRol(id);

    }


    @PatchMapping("/actualizarusuario/{id}")
    public ResponseEntity <ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clientedto){

        ClienteDTO cliente = clienteService.actualizarCliente(id,clientedto);

        if (cliente != null){
            return ResponseEntity.ok(cliente);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PatchMapping("/cambiopassword/{id}")
    public ResponseEntity<String> cambioPassword(@PathVariable Long id, @RequestBody CambioPasswordDTO cambiopassword){

        Boolean cambio= clienteService.cambiarPassword(id,cambiopassword.getPasswordActual(),cambiopassword.getPasswordNueva());

        if (cambio) {
            return ResponseEntity.ok("Contraseña actualizada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("La contraseña actual no es válida o el usuario no existe.");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginRequest) {
        boolean authenticated = clienteService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (authenticated) {
            return ResponseEntity.ok("Login exitoso.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = clienteService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

}
