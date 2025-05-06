package cl.Ferramas.Ferramas.mapper;


import cl.Ferramas.Ferramas.dto.DetallePedidoDTO;
import cl.Ferramas.Ferramas.entity.DetallePedido;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    public DetallePedidoDTO toDTO(DetallePedido detalle) {
        if (detalle == null) {
            return null;
        }

        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detalle.getDetallePedidoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setDescuentounitario(detalle.getDescuentoUnitario());
        dto.setTotal(detalle.getTotalLinea());


        return dto;
    }

    public DetallePedido toEntity(DetallePedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        DetallePedido detalle = new DetallePedido();
        detalle.setDetallePedidoId(dto.getId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setDescuentoUnitario(dto.getDescuentounitario());
        detalle.setTotalLinea(dto.getTotal());


        return detalle;
    }
}
