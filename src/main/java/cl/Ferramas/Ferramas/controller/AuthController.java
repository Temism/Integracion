package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.LoginDTO;
import cl.Ferramas.Ferramas.entity.Usuario;
import cl.Ferramas.Ferramas.repository.UsuarioRep;
import cl.Ferramas.Ferramas.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRep usuarioRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        Usuario usuario = usuarioRep.findByEmail(loginRequest.getEmail()).orElse(null);

        if (usuario != null && passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        }

        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
}
