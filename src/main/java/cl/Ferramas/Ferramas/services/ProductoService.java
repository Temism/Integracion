package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroClienteDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.dto.UsuarioDTO;
import cl.Ferramas.Ferramas.entity.*;

import cl.Ferramas.Ferramas.mapper.ProductoMapper;
import cl.Ferramas.Ferramas.repository.CategoriaRep;
import cl.Ferramas.Ferramas.repository.MarcaRep;
import cl.Ferramas.Ferramas.repository.ProductoRep;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productorep.findAll();
        return productos.stream()
                .map(productoMapper::productotoProductoDTO)
                .collect(Collectors.toList());
    }
}