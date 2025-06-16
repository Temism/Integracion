package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.dto.MovimientoInventarioDTO;
import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.entity.*;

import cl.Ferramas.Ferramas.mapper.MovimientoInventarioMapper;
import cl.Ferramas.Ferramas.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRep movimientoInventarioRepository;
    @Autowired
    private MovimientoInventarioMapper movimientoInventarioMapper;
    @Autowired
    private UsuarioRep usuarioRep;
    @Autowired
    private SucursalRep sucursalRep;
    @Autowired
    private ProductoRep productoRep;
    @Autowired
    private PedidoRep pedidoRep;
    @Autowired
    private TipoMovimientoInventarioRep tiporep;



    public List<MovimientoInventarioDTO> BuscarTodosLosMovimientos() {
        List<MovimientoInventario> movimiento = movimientoInventarioRepository.findAll();
        return movimiento.stream()
                .map(movimientoInventarioMapper::toDTO)
                .collect(Collectors.toList());
    }




    @Transactional
    public MovimientoInventarioDTO BuscarMovimientoPorId(Long id){
        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(id);

        return movimientoInventario.map(movimientoInventarioMapper::toDTO).orElse(null);
    }


    @Transactional
    public MovimientoInventarioDTO guardarMovimientoInventario(MovimientoInventarioDTO movimientoInventarioDTO) {


        MovimientoInventario Movimiento = movimientoInventarioMapper.toEntity(movimientoInventarioDTO);

        Usuario usuario = usuarioRep.findById(movimientoInventarioDTO.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Movimiento.setUsuario(usuario);

        Sucursal sucursal = sucursalRep.findById(movimientoInventarioDTO.getIdSucursal()).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        Movimiento.setSucursal(sucursal);

        Producto producto = productoRep.findById(movimientoInventarioDTO.getIdproducto()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Movimiento.setProducto(producto);

        Pedido pedido = pedidoRep.findById(movimientoInventarioDTO.getIdPedido()).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Movimiento.setPedido(pedido);

        TipoMovimientoInventario tipoMovimientoInventario = tiporep.findById(movimientoInventarioDTO.getIdtipomov()).orElseThrow(() -> new RuntimeException("Tipo de movimiento no encontrado"));
        Movimiento.setTipoMovimiento(tipoMovimientoInventario);



        Movimiento = movimientoInventarioRepository.save(Movimiento);

        return movimientoInventarioMapper.toDTO(Movimiento);
    }



}