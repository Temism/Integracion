package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.entity.*;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getPedidoId());
        dto.setCodigo(pedido.getCodigoPedido());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setDireccionEntrega(pedido.getDireccionEntrega());
        dto.setSubtotal(pedido.getSubtotal());
        dto.setTotal(pedido.getTotal());
        dto.setDescuento(pedido.getDescuento());
        dto.setIva(pedido.getIva());
        dto.setNota(pedido.getNotas());
        dto.setComprobanteUrl(pedido.getComprobanteUrl());


        dto.setEstado(pedido.getEstado() != null ? pedido.getEstado().getNombre() : null);
        dto.setTipoentrega(pedido.getTipoEntrega() != null ? pedido.getTipoEntrega().getNombre() : null);


        dto.setEstadoId(pedido.getEstado() != null ? pedido.getEstado().getEstadoPedidoId() : null);
        dto.setClienteId(pedido.getCliente() != null ? pedido.getCliente().getUsuarioId() : null);
        dto.setVendedorId(pedido.getVendedor() != null ? pedido.getVendedor().getUsuarioId() : null);
        dto.setSucursalId(pedido.getSucursal() != null ? pedido.getSucursal().getSucursalId() : null);
        dto.setTipoEntregaId(pedido.getTipoEntrega() != null ? pedido.getTipoEntrega().getTipoEntregaId() : null);
        dto.setMetodoPago(pedido.getMetodoPago() != null ? pedido.getMetodoPago().getMetodoId() : null);

        return dto;
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setPedidoId(dto.getId());
        pedido.setCodigoPedido(dto.getCodigo());
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setDireccionEntrega(dto.getDireccionEntrega());
        pedido.setSubtotal(dto.getSubtotal());
        pedido.setTotal(dto.getTotal());
        pedido.setDescuento(dto.getDescuento());
        pedido.setIva(dto.getIva());
        pedido.setNotas(dto.getNota());
        pedido.setComprobanteUrl(dto.getComprobanteUrl());


        if (dto.getClienteId() != null) {
            Usuario cliente = new Usuario();
            cliente.setUsuarioId(dto.getClienteId());
            pedido.setCliente(cliente);
        }

        if (dto.getVendedorId() != null) {
            Usuario vendedor = new Usuario();
            vendedor.setUsuarioId(dto.getVendedorId());
            pedido.setVendedor(vendedor);
        }

        if (dto.getEstadoId() != null) {
            EstadoPedido estado = new EstadoPedido();
            estado.setEstadoPedidoId(dto.getEstadoId());
            pedido.setEstado(estado);
        }

        if (dto.getTipoEntregaId() != null) {
            TipoEntrega tipoEntrega = new TipoEntrega();
            tipoEntrega.setTipoEntregaId(dto.getTipoEntregaId());
            pedido.setTipoEntrega(tipoEntrega);
        }

        if (dto.getSucursalId() != null) {
            Sucursal sucursal = new Sucursal();
            sucursal.setSucursalId(dto.getSucursalId());
            pedido.setSucursal(sucursal);
        }

        if (dto.getMetodoPago() != null) {
            MetodoPago metodoPago = new MetodoPago();
            metodoPago.setMetodoId(dto.getMetodoPago());
            pedido.setMetodoPago(metodoPago);
        }

        return pedido;
    }
}