package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.InventarioDTO;
import cl.Ferramas.Ferramas.entity.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioDTO toDTO(Inventario inventario) {
        if (inventario == null) {
            return null;
        }

        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getInventarioId());
        dto.setStockActual(inventario.getStockActual());
        dto.setStockMinimo(inventario.getStockMinimo());
        dto.setUbicacion(inventario.getUbicacion());
        dto.setProducto(inventario.getProducto().getNombre());


        return dto;
    }

    public Inventario toEntity(InventarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Inventario inventario = new Inventario();
        inventario.setInventarioId(dto.getId());
        inventario.setStockActual(dto.getStockActual());
        inventario.setStockMinimo(dto.getStockMinimo());
        inventario.setUbicacion(dto.getUbicacion());


        return inventario;
    }
}
