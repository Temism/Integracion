package cl.Ferramas.Ferramas.services;

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

import java.math.BigDecimal;
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
        Usuario Vendedor = usuarioRep.findById(pedido.getVendedor().getUsuarioId()).orElseThrow(() -> new RuntimeException("Vendedor no encontrada"));
        pedido.setVendedor(Vendedor);
        TipoEntrega tipoEntrega = tipoEntregaRep.findById(pedido.getTipoEntrega().getTipoEntregaId()).orElseThrow(() -> new RuntimeException("tipo de entrega no encontrada"));
        pedido.setTipoEntrega(tipoEntrega);
        Sucursal sucursal = sucursalRep.findById(pedido.getSucursal().getSucursalId()).orElseThrow(() -> new RuntimeException("Vendedor no encontrada"));
        pedido.setSucursal(sucursal);
        pedido = pedidoRep.save(pedido);

        return pedidoMapper.toDTO(pedido);
    }






}

