package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Producto;

import cl.Ferramas.Ferramas.repository.ProductoRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {


    private final ProductoRep productoRepository;


    @Autowired
    public ProductoService(ProductoRep productoRepository) {
        this.productoRepository = productoRepository;

    }

    public List<Producto> BuscarTodosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> BuscarPorId(Long productoId) {
        return productoRepository.findById(productoId);
    }

    public Producto GuardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void EliminarProducto(Long productoId) {
        productoRepository.deleteById(productoId);

    /*public Optional<Producto> findByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    public List<Producto> findByMarcaId(Long marcaId) {
        return productoRepository.findByMarcaId(marcaId);
    }

    public List<Producto> findByCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriasId(categoriaId);
    }

    public List<Producto> findByNombreContaining(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }*/


}
}