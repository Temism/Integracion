package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.dto.DespachoDTO;
import cl.Ferramas.Ferramas.dto.DetallePedidoDTO;
import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.mapper.DetallePedidoMapper;
import cl.Ferramas.Ferramas.repository.DetallePedidoRep;
import cl.Ferramas.Ferramas.repository.PedidoRep;
import cl.Ferramas.Ferramas.repository.ProductoRep;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class DetallePedidoService {


    @Autowired
    private DetallePedidoRep detallePedidoRep;

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    @Autowired
    private PedidoRep pedidoRep;

    @Autowired
    private ProductoRep productoRep;

    @Transactional
    public DetallePedidoDTO buscardetallepedidoporId(Long id){
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRep.findById(id);

        return detallePedidoOptional.map(detallePedidoMapper::toDTO).orElse(null);
    }

    @Transactional
    public List<DetallePedidoDTO> listarDetallesPedidos() {
        List<DetallePedido> detalle = detallePedidoRep.findAll();
        return detalle.stream()
                .map(detallePedidoMapper::toDTO)
                .collect(Collectors.toList());
    }



    public void eliminarDetallePedido(Long detallePedidoId) {
        detallePedidoRep.deleteById(detallePedidoId);
    }

    @Transactional
    public DetallePedidoDTO registrarproducto(DetallePedidoDTO detallePedidoDTO) {

        DetallePedido detallePedido = detallePedidoMapper.toEntity(detallePedidoDTO);

        Pedido pedido = pedidoRep.findById(detallePedidoDTO.getPedidoId()).orElseThrow(() -> new RuntimeException("Pedido no encontrada"));
        detallePedido.setPedido(pedido);

        Producto producto = productoRep.findById(detallePedidoDTO.getProductoId()).orElseThrow(() -> new RuntimeException("Producto no encontrada"));
        detallePedido.setProducto(producto);
        detallePedido = detallePedidoRep.save(detallePedido);

        return detallePedidoMapper.toDTO(detallePedido);
    }

}