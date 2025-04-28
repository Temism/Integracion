package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Categoria;
import cl.Ferramas.Ferramas.entity.Producto;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.CategoriaRep;

import cl.Ferramas.Ferramas.repository.ProductoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRep categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Long categoriaId) {
        return categoriaRepository.findById(categoriaId);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void BorrarCategoria(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
    }

}
