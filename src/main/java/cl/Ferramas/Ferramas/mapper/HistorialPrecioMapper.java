package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.HistorialPrecioDTO;
import cl.Ferramas.Ferramas.entity.HistorialPrecio;

public class HistorialPrecioMapper {

    public static HistorialPrecioDTO toDTO(HistorialPrecio historial) {
        HistorialPrecioDTO dto = new HistorialPrecioDTO();
        dto.setId(historial.getHistorialPrecioId());
        dto.setProductoId(historial.getProducto().getProductoId());
        dto.setNombreProducto(historial.getProducto().getNombre());
        dto.setPrecioAnterior(historial.getPrecioAnterior());
        dto.setPrecioNuevo(historial.getPrecioNuevo());
        dto.setFechaCambio(historial.getFechaCambio());
        dto.setMotivo(historial.getMotivo());
        dto.setNombreUsuario(historial.getUsuario() != null ? historial.getUsuario().getNombre() : "Sistema");
        return dto;
    }
}
