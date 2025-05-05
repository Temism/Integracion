package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.dto.UsuarioDTO;
import cl.Ferramas.Ferramas.entity.Producto;
import cl.Ferramas.Ferramas.entity.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductoMapper {




    public Producto productoDTOToProducto(RegistroProductoDTO producto){
        if (producto == null){
            return null;
        }
        Producto prod = new Producto();

        prod.setNombre(producto.getNombre());
        prod.setCodigo(producto.getCodigo());
        prod.setCosto(producto.getCosto());
        prod.setPrecioActual(producto.getPrecioActual());
        prod.setDescripcion(producto.getDescripcion());
        prod.setGarantiaMeses(producto.getGarantiaMeses());
        prod.setFechaCreacion(LocalDate.now());
        prod.setActivo(true);


        return prod;
    }


    public RegistroProductoDTO registropToDTO(Producto producto){
        if (producto == null){
            return null;
        }

        RegistroProductoDTO prod = new RegistroProductoDTO();


        prod.setNombre(producto.getNombre());
        prod.setCategoria(producto.getCategoria().getCategoriaId());
        prod.setCodigo(producto.getCodigo());
        prod.setCosto(producto.getCosto());
        prod.setPrecioActual(producto.getPrecioActual());
        prod.setDescripcion(producto.getDescripcion());
        prod.setGarantiaMeses(producto.getGarantiaMeses());
        prod.setFechaCreacion(LocalDate.now());
        prod.setMarca(producto.getMarca().getMarcaId());



        return prod;
    }


    public ProductoDTO productotoProductoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setId(producto.getProductoId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setMarca(producto.getMarca().getNombre());
        productoDTO.setCategoria(producto.getCategoria().getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setActivo(producto.getActivo());
        productoDTO.setCosto(producto.getCosto());
        productoDTO.setCodigo(producto.getCodigo());
        productoDTO.setGarantiaMeses(producto.getGarantiaMeses());
        productoDTO.setPrecioActual(producto.getPrecioActual());
        productoDTO.setFechaCreacion(producto.getFechaCreacion());
        productoDTO.setFechaActualizacion(producto.getFechaActualizacion());



        return productoDTO;
    }

}
