package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.entity.Pedido;
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
        dto.setEstado(pedido.getEstado().getNombre());
        dto.setTipoentrega(pedido.getTipoEntrega().getNombre());
        dto.setComprobanteUrl(pedido.getComprobanteUrl());
        dto.setMetodoPago(pedido.getMetodoPago());



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
        pedido.setMetodoPago(dto.getMetodoPago());




        return pedido;
    }


}