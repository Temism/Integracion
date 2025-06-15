package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.CategoriaDTO;
import cl.Ferramas.Ferramas.entity.Categoria;
import cl.Ferramas.Ferramas.mapper.CategoriaMapper;
import cl.Ferramas.Ferramas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAll() {
        return categoriaService.listarCategorias()
                .stream()
                .map(CategoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getById(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.toDTO(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoriaDTO create(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria nueva = categoriaService.guardarCategoria(CategoriaMapper.toEntity(categoriaDTO));
        notificarCambioCache(); // Invalida caché en Express
        return CategoriaMapper.toDTO(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(existing -> {
                    existing.setNombre(categoriaDTO.getNombre());
                    existing.setDescripcion(categoriaDTO.getDescripcion());
                    Categoria actualizada = categoriaService.guardarCategoria(existing);
                    notificarCambioCache(); // Invalida caché en Express
                    return ResponseEntity.ok(CategoriaMapper.toDTO(actualizada));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (categoriaService.buscarCategoriaPorId(id).isPresent()) {
            categoriaService.BorrarCategoria(id);
            notificarCambioCache(); // Invalida caché en Express
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void notificarCambioCache() {
        String url = "http://localhost:3000/cache/clear/ruta";

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("ruta", "/java-api/categoria");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
