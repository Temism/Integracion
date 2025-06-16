package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.dto.CambioEstadoDTO;
import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.mapper.PedidoMapper;
import cl.Ferramas.Ferramas.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRep pedidoRep;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private UsuarioRep usuarioRep;
    @Autowired
    private EstadoPedidoRep estadoPedidoRep;
    @Autowired
    private TipoEntregaRep tipoEntregaRep;
    @Autowired
    private SucursalRep sucursalRep;
    @Autowired
    private HistorialEstadoPedidoRep historialEstadoPedidoRep;
    @Autowired
    private MetodoPagoRep metodoPagoRep;

    @Transactional
    public List<PedidoDTO> listarpedidos() {
        List<Pedido> pedidos = pedidoRep.findAll();
        return pedidos.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO buscarpedidoporId(Long id){
        Optional<Pedido> pedidoOptional = pedidoRep.findById(id);
        return pedidoOptional.map(pedidoMapper::toDTO).orElse(null);
    }

    @Transactional
    public PedidoDTO registrarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

        Usuario cliente = usuarioRep.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        pedido.setCliente(cliente);

        Usuario vendedor = usuarioRep.findById(pedidoDTO.getVendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        pedido.setVendedor(vendedor);

        EstadoPedido estado = estadoPedidoRep.findById(pedidoDTO.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        pedido.setEstado(estado);

        TipoEntrega tipoEntrega = tipoEntregaRep.findById(pedidoDTO.getTipoEntregaId())
                .orElseThrow(() -> new RuntimeException("Tipo de entrega no encontrado"));
        pedido.setTipoEntrega(tipoEntrega);

        Sucursal sucursal = sucursalRep.findById(pedidoDTO.getSucursalId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        pedido.setSucursal(sucursal);

        MetodoPago metodoPago = metodoPagoRep.findById(pedidoDTO.getMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        pedido.setMetodoPago(metodoPago);

        pedido = pedidoRep.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    @Transactional
    public void actualizarComprobante(Long pedidoId, String url) {
        Pedido pedido = pedidoRep.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setComprobanteUrl(url);
        pedidoRep.save(pedido);
    }

    @Transactional
    public boolean cambiarEstadoPedido(Long pedidoId, CambioEstadoDTO dto) {
        Optional<Pedido> optionalPedido = pedidoRep.findById(pedidoId);
        if (optionalPedido.isEmpty()) return false;

        Pedido pedido = optionalPedido.get();
        EstadoPedido estadoAnterior = pedido.getEstado();

        EstadoPedido nuevoEstado = estadoPedidoRep.findById(dto.getEstadoNuevoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Usuario usuario = usuarioRep.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        pedido.setEstado(nuevoEstado);
        pedidoRep.save(pedido);

        HistorialEstadoPedido historial = new HistorialEstadoPedido();
        historial.setPedido(pedido);
        historial.setEstadoAnterior(estadoAnterior);
        historial.setEstadoNuevo(nuevoEstado);
        historial.setUsuario(usuario);
        historial.setFechaCambio(LocalDateTime.now());
        historial.setComentario(dto.getComentario());

        historialEstadoPedidoRep.save(historial);

        return true;
    }
}
