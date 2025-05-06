package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.dto.DespachoDTO;

import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.exception.Controllerexception;
import cl.Ferramas.Ferramas.mapper.DespachoMapper;
import cl.Ferramas.Ferramas.repository.DespachoRep;
import cl.Ferramas.Ferramas.repository.EstadoDespachoRep;
import cl.Ferramas.Ferramas.repository.PedidoRep;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DespachoService {

    @Autowired
    private DespachoRep despachoRep;

    @Autowired
    private DespachoMapper despachoMapper;

    @Autowired
    private EstadoDespachoRep estadoDespachoRep;

    @Autowired
    private PedidoRep pedidoRep;

    @Transactional
    public DespachoDTO buscardespachoporId(Long id){
        Optional<Despacho> despachoOptional = despachoRep.findById(id);

        return despachoOptional.map(despachoMapper::toDTO).orElse(null);
    }


    @Transactional
    public List<DespachoDTO> listardespachos() {
        List<Despacho> despachos = despachoRep.findAll();
        return despachos.stream()
                .map(despachoMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public DespachoDTO registrarproducto(DespachoDTO despachoDTO) {

        Despacho despacho = despachoMapper.toEntity(despachoDTO);

        Pedido pedido = pedidoRep.findById(despachoDTO.getPedidoId()).orElseThrow(() -> new RuntimeException("Pedido no encontrada"));
        despacho.setPedido(pedido);

        EstadoDespacho estadoDespacho = estadoDespachoRep.findById(despachoDTO.getEstadiId()).orElseThrow(() -> new RuntimeException("Estado Despacho no encontrada"));
        despacho.setEstado(estadoDespacho);
        despacho = despachoRep.save(despacho);

        return despachoMapper.toDTO(despacho);
    }

    public DespachoDTO actualizardespacho(Long id,DespachoDTO despachoDTO) {
        // busca el producto
        Despacho despacho = despachoRep.findById(id).orElseThrow(() -> new Controllerexception("Despacho con ID " + id + " no encontrado"));

        // actualiza campos basicos(sin el precio)
        if (despachoDTO.getFechaDespacho() != null) {
            despacho.setFechaDespacho(despachoDTO.getFechaDespacho());
        }
        if (despachoDTO.getFechaEntregaEstimada() != null) {
            despacho.setFechaEntregaEstimada(despachoDTO.getFechaEntregaEstimada());
        }
        if (despachoDTO.getFechaEntregaReal() != null) {
            despacho.setFechaEntregaReal(despachoDTO.getFechaEntregaReal());
        }

        if (despachoDTO.getPedidoId() != null) {
            Pedido pedido = pedidoRep.findById(despachoDTO.getPedidoId())
                    .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
            despacho.setPedido(pedido);
        }

        if (despachoDTO.getEstadiId() != null) {
            EstadoDespacho estado = estadoDespachoRep.findById(despachoDTO.getEstadiId())
                    .orElseThrow(() -> new EntityNotFoundException("Estado de despacho no encontrado"));
            despacho.setEstado(estado);
        }

        // Guardar y retornar DTO
        despachoRep.save(despacho);
        return despachoMapper.toDTO(despacho);
    }




}
