package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.dto.*;
import cl.Ferramas.Ferramas.entity.*;

import cl.Ferramas.Ferramas.exception.Controllerexception;
import cl.Ferramas.Ferramas.mapper.ProductoMapper;
import cl.Ferramas.Ferramas.repository.CategoriaRep;
import cl.Ferramas.Ferramas.repository.MarcaRep;
import cl.Ferramas.Ferramas.repository.ProductoRep;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {



    @Autowired
    ProductoMapper productoMapper;

    @Autowired
    ProductoRep productorep;
    @Autowired
    CategoriaRep categoriaRep;
    @Autowired
    MarcaRep marcaRep;

    @Transactional
    public RegistroProductoDTO registrarproducto(RegistroProductoDTO registroDTO) {


        Producto producto = productoMapper.productoDTOToProducto(registroDTO);

        Categoria categoria = categoriaRep.findById(registroDTO.getCategoria()).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        producto.setCategoria(categoria);

        Marca marca = marcaRep.findById(registroDTO.getMarca()).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        producto.setMarca(marca);

        producto = productorep.save(producto);

        return productoMapper.registropToDTO(producto);
    }

    @Transactional
    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productorep.findAll();
        return productos.stream()
                .map(productoMapper::productotoProductoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductoDTO buscarprodporId(Long id){
        Optional<Producto> productoOptional = productorep.findById(id);

        return productoOptional.map(productoMapper::productotoProductoDTO).orElse(null);
    }

    public RegistroProductoDTO actualizarProducto(Long id,RegistroProductoDTO productoDTO) {
        // busca el producto
        Producto producto = productorep.findById(id).orElseThrow(() -> new Controllerexception("Producto con ID " + id + " no encontrado"));

        // actualiza campos basicos(sin el precio)
        if (productoDTO.getNombre() != null) {
            producto.setNombre(productoDTO.getNombre());
        }
        if (productoDTO.getCodigo() != null) {
            producto.setCodigo(productoDTO.getCodigo());
        }
        if (productoDTO.getDescripcion() != null) {
            producto.setDescripcion(productoDTO.getDescripcion());
        }
        if (productoDTO.getMarca() != null) {
            Marca marca = marcaRep.findById(productoDTO.getMarca())
                    .orElseThrow(() -> new EntityNotFoundException("Marca no encontrado"));
            producto.setMarca(marca);
        }
        if (productoDTO.getCategoria() != null) {
            Categoria categoria = categoriaRep.findById(productoDTO.getCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Marca no encontrado"));
            producto.setCategoria(categoria);
        }
        if (productoDTO.getGarantiaMeses() != null) {
            producto.setGarantiaMeses(productoDTO.getGarantiaMeses());
        }
        if (productoDTO.getActivo() != null) {
            producto.setActivo(productoDTO.getActivo());
        }
        producto.setFechaActualizacion(LocalDate.now());

        // Guardar y retornar DTO
        productorep.save(producto);
        return productoMapper.registropToDTO(producto);
    }



}