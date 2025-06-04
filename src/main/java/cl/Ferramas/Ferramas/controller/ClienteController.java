package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.CambioPasswordDTO;
import cl.Ferramas.Ferramas.dto.ClienteDTO;
import cl.Ferramas.Ferramas.dto.RegistroClienteDTO;
import cl.Ferramas.Ferramas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService clienteService;


    @PostMapping
    public ResponseEntity<RegistroClienteDTO> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroDTO) {
        RegistroClienteDTO clienteRegistrado = clienteService.registrarCliente(registroDTO);
        return new ResponseEntity<>(clienteRegistrado, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClienteDTO> getAll() {
        return clienteService.listarClientes();
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


}
