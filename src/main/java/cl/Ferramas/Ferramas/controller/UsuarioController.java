package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.*;
<<<<<<< HEAD

=======
>>>>>>> b9d1bc374d63426053770a329cbd726bf2d9ee49
import cl.Ferramas.Ferramas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService clienteService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<RegistroUsuarioDTO> registrarCliente(@Valid @RequestBody RegistroUsuarioDTO registroDTO) {
        RegistroUsuarioDTO usuarioregistrado = clienteService.registrarUsuario(registroDTO);
        notificarCambioCache(); // Invalida caché en Express
        return new ResponseEntity<>(usuarioregistrado, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UsuarioDTO> getAll() {
        return clienteService.listarUsuarios();
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

    @GetMapping("/usuarioporsucursal/{id}")
    public List<UsuarioDTO> usuariosporsucursal(@PathVariable Long id) {
        return clienteService.listarUsuariosPorSucursal(id);
    }

    @GetMapping("/usuarioporrol/{id}")
    public List<UsuarioDTO> usuarioPorRol(@PathVariable Long id) {
        return clienteService.listarUsuariosPorRol(id);
    }

    @PatchMapping("/actualizarusuario/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clientedto) {
        ClienteDTO cliente = clienteService.actualizarCliente(id, clientedto);
        if (cliente != null) {
            notificarCambioCache(); // Invalida caché en Express
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/cambiopassword/{id}")
    public ResponseEntity<String> cambioPassword(@PathVariable Long id, @RequestBody CambioPasswordDTO cambiopassword) {
        Boolean cambio = clienteService.cambiarPassword(id, cambiopassword.getPasswordActual(), cambiopassword.getPasswordNueva());
        if (cambio) {
            notificarCambioCache(); // Invalida caché en Express
            return ResponseEntity.ok("Contraseña actualizada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("La contraseña actual no es válida o el usuario no existe.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            return ResponseEntity.ok("Login exitoso. Bienvenido, " + auth.getName());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error durante la autenticación.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = clienteService.eliminarUsuario(id);
        if (eliminado) {
            notificarCambioCache(); // Invalida caché en Express
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    // Método auxiliar para notificar a Express y limpiar la caché
    private void notificarCambioCache() {
        String url = "http://localhost:3000/cache/clear/ruta";

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("ruta", "/java-api/usuario");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
