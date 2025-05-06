package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.dto.InventarioDTO;

import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.exception.Controllerexception;
import cl.Ferramas.Ferramas.mapper.InventarioMapper;
import cl.Ferramas.Ferramas.repository.InventarioRep;
import cl.Ferramas.Ferramas.repository.ProductoRep;
import cl.Ferramas.Ferramas.repository.SucursalRep;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class InventarioService {

    @Autowired
    private InventarioRep inventarioRep;
    @Autowired
    private InventarioMapper inventarioMapper;
    @Autowired
    private ProductoRep productoRep;
    @Autowired
    private SucursalRep sucursalRep;


    public List<Inventario> listarInventario(){
        return inventarioRep.findAll();
    }

    @Transactional
    public InventarioDTO buscarInventarioporId(Long id){
        Optional<Inventario> inventarioOptional = inventarioRep.findById(id);

        return inventarioOptional.map(inventarioMapper::toDTO).orElse(null);
    }


    @Transactional
    public InventarioDTO guardarInventario(InventarioDTO inventarioDTO) {


        Inventario inventario = inventarioMapper.toEntity(inventarioDTO);

       inventario.setStockActual(inventarioDTO.getStockActual());
       inventario.setStockMinimo(inventarioDTO.getStockMinimo());
       inventario.setUbicacion(inventarioDTO.getUbicacion());


        Producto producto = productoRep.findById(inventarioDTO.getProductoId()).orElseThrow(() -> new RuntimeException("Producto no encontrada"));
        inventario.setProducto(producto);
        Sucursal sucursal = sucursalRep.findById(inventarioDTO.getSucursalId()).orElseThrow(() -> new RuntimeException("Producto no encontrada"));
        inventario.setSucursal(sucursal);

        inventario= inventarioRep.save(inventario);


        return inventarioMapper.toDTO(inventario);
    }

    public InventarioDTO actualizarInventario(Long id, InventarioDTO inventarioDTO) {
        // busca el Inventario
        Inventario inventario = inventarioRep.findById(id).orElseThrow(() -> new Controllerexception("Inventario con ID " + id + " no encontrado"));

        // actualiza campos
        if (inventarioDTO.getStockActual() != null) {
            inventario.setStockActual(inventarioDTO.getStockActual());
        }
        if (inventarioDTO.getUbicacion() != null) {
            inventario.setUbicacion(inventarioDTO.getUbicacion());
        }

        // Guardar y retornar DTO
        inventarioRep.save(inventario);
        return inventarioMapper.toDTO(inventario);
    }

    public List<InventarioDTO> obtenerInventariosPorSucursal(Long sucursalId) {
        List<Inventario> inventarios = inventarioRep.inventarioporsucursal(sucursalId);
        return inventarios.stream()
                .map(inventarioMapper::toDTO)
                .toList();
    }




}