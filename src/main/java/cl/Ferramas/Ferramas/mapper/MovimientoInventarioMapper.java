package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.MovimientoInventarioDTO;
import cl.Ferramas.Ferramas.entity.*;
import org.springframework.stereotype.Component;

@Component
public class MovimientoInventarioMapper {

    public MovimientoInventarioDTO toDTO(MovimientoInventario movimiento) {
        if (movimiento == null) {
            return null;
        }

        MovimientoInventarioDTO dto = new MovimientoInventarioDTO();
        dto.setId(movimiento.getMovimientoInventarioId());
        dto.setCantidad(movimiento.getCantidad());
        dto.setFechaMovimiento(movimiento.getFechaMovimiento());
        dto.setMotivo(movimiento.getMotivo());
        dto.setIdproducto(movimiento.getProducto() != null ? movimiento.getProducto().getProductoId() : null);
        dto.setIdPedido(movimiento.getPedido() != null ? movimiento.getPedido().getPedidoId() : null);
        dto.setIdSucursal(movimiento.getSucursal() != null ? movimiento.getSucursal().getSucursalId() : null);
        dto.setIdUsuario(movimiento.getUsuario() != null ? movimiento.getUsuario().getUsuarioId() : null);
        dto.setIdtipomov(movimiento.getTipoMovimiento() != null ? movimiento.getTipoMovimiento().getTipoMovimientoInventarioId() : null );

        return dto;
    }

    public MovimientoInventario toEntity(MovimientoInventarioDTO dto) {
        if (dto == null) {
            return null;
        }

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setMovimientoInventarioId(dto.getId());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setFechaMovimiento(dto.getFechaMovimiento());
        movimiento.setMotivo(dto.getMotivo());



        if (dto.getIdproducto() != null) {
            Producto producto = new Producto();
            producto.setProductoId(dto.getIdproducto());
            movimiento.setProducto(producto);
        }

        if (dto.getIdPedido() != null) {
            Pedido pedido = new Pedido();
            pedido.setPedidoId(dto.getIdproducto());
            movimiento.setPedido(pedido);
        }

        if (dto.getIdSucursal() != null) {
            Sucursal sucursal = new Sucursal();
            sucursal.setSucursalId(dto.getIdSucursal());
            movimiento.setSucursal(sucursal);
        }

        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(dto.getIdUsuario());
            movimiento.setUsuario(usuario);
        }

        if (dto.getIdtipomov() != null) {
            TipoMovimientoInventario tipoMovimientoInventario = new TipoMovimientoInventario();
            tipoMovimientoInventario.setTipoMovimientoInventarioId(dto.getIdtipomov());
            movimiento.setTipoMovimiento(tipoMovimientoInventario);
        }




        return movimiento;
    }
}
