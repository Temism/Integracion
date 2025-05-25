package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.dto.CambioEstadoDTO;
import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.mapper.PedidoMapper;
import cl.Ferramas.Ferramas.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

        Usuario cliente = usuarioRep.findById(pedido.getCliente().getUsuarioId()).orElseThrow(() -> new RuntimeException("Cliente no encontrada"));
        pedido.setCliente(cliente);

        EstadoPedido estado = estadoPedidoRep.findById(pedido.getEstado().getEstadoPedidoId()).orElseThrow(() -> new RuntimeException("estado no encontrada"));
        pedido.setEstado(estado);

        Usuario vendedor = usuarioRep.findById(pedido.getVendedor().getUsuarioId()).orElseThrow(() -> new RuntimeException("Vendedor no encontrada"));
        pedido.setVendedor(vendedor);

        TipoEntrega tipoEntrega = tipoEntregaRep.findById(pedido.getTipoEntrega().getTipoEntregaId()).orElseThrow(() -> new RuntimeException("tipo de entrega no encontrada"));
        pedido.setTipoEntrega(tipoEntrega);

        Sucursal sucursal = sucursalRep.findById(pedido.getSucursal().getSucursalId()).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        pedido.setSucursal(sucursal);

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

    // ✅ NUEVO: Cambiar estado del pedido y registrar en historial
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

        // Actualiza el pedido
        pedido.setEstado(nuevoEstado);
        pedidoRep.save(pedido);

        // Registra en el historial
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
