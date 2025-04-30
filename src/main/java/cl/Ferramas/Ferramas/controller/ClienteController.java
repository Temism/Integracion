package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.ClienteDTO;
import cl.Ferramas.Ferramas.dto.RegistroClienteDTO;
import cl.Ferramas.Ferramas.dto.UsuarioDTO;
import cl.Ferramas.Ferramas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private UsuarioService clienteService;


    @PostMapping("/registrocli")
    public ResponseEntity<RegistroClienteDTO> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroDTO) {
        RegistroClienteDTO clienteRegistrado = clienteService.registrarCliente(registroDTO);
        return new ResponseEntity<>(clienteRegistrado, HttpStatus.CREATED);
    }

    @GetMapping("/listaclientes")
    public List<ClienteDTO> getAll() {
        return clienteService.listarClientes();
    }


}
