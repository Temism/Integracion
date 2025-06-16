package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.MovimientoInventarioDTO;
import cl.Ferramas.Ferramas.dto.PagoDTO;
import cl.Ferramas.Ferramas.entity.*;

public class PagoMapper {

    public PagoDTO toDTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getPagoId());
        dto.setCodigoTransaccion(pago.getCodigoTransaccion());
        dto.setFechapago(pago.getFechaPago());
        dto.setMonto(pago.getMonto());
        dto.setDatosTransaccion(pago.getDatosTransaccion());
        dto.setIdEstado(pago.getEstado() != null ? pago.getEstado().getEstadoPagoId(): null);
        dto.setIdMetodopago(pago.getMetodoPago() != null ? pago.getMetodoPago().getMetodoId(): null);
        dto.setIdPedido(pago.getPedido() != null ? pago.getPedido().getPedidoId(): null);

        return dto;
    }



    public Pago toEntity(PagoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pago pago = new Pago();
        pago.setPagoId(dto.getId());
        pago.setCodigoTransaccion(dto.getCodigoTransaccion());
        pago.setFechaPago(dto.getFechapago());
        pago.setMonto(dto.getMonto());
        pago.setDatosTransaccion(dto.getDatosTransaccion());


        if (dto.getIdEstado() != null) {
            EstadoPago estadoPago = new EstadoPago();
            estadoPago.setEstadoPagoId(dto.getIdEstado());
            pago.setEstado(estadoPago);
        }
        if (dto.getIdMetodopago() != null){
            MetodoPago metodoPago = new MetodoPago();
            metodoPago.setMetodoId(dto.getIdMetodopago());
            pago.setMetodoPago(metodoPago);
        }
        if (dto.getIdPedido() != null){
            Pedido pedido = new Pedido();
            pedido.setPedidoId(dto.getIdPedido());
            pago.setPedido(pedido);
        }

        return pago;
    }
}