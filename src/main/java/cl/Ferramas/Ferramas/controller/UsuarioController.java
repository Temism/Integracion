package cl.Ferramas.Ferramas.controller;




import cl.Ferramas.Ferramas.dto.RegistroUsuarioDTO;
import cl.Ferramas.Ferramas.dto.UsuarioDTO;
import cl.Ferramas.Ferramas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService clienteService;

    @PostMapping("/registrouser")
    public ResponseEntity<RegistroUsuarioDTO> registrarCliente(@Valid @RequestBody RegistroUsuarioDTO registroDTO) {
        RegistroUsuarioDTO usuarioregistrado = clienteService.registrarUsuario(registroDTO);
        return new ResponseEntity<>(usuarioregistrado, HttpStatus.CREATED);
    }


    @GetMapping("/ListaUsuarios")
    public List<UsuarioDTO> getAll() {
        return clienteService.listarUsuarios();
    }





}
