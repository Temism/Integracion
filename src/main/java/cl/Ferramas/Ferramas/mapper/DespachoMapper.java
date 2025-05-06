package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.DespachoDTO;
import cl.Ferramas.Ferramas.entity.Despacho;
import org.springframework.stereotype.Component;

@Component
public class DespachoMapper {

    public DespachoDTO toDTO(Despacho despacho) {
        if (despacho == null) {
            return null;
        }

        DespachoDTO dto = new DespachoDTO();
        dto.setId(despacho.getDespachoId());
        dto.setFechaDespacho(despacho.getFechaDespacho());
        dto.setFechaEntregaEstimada(despacho.getFechaEntregaEstimada());
        dto.setFechaEntregaReal(despacho.getFechaEntregaReal());
        dto.setNumeroGuia(despacho.getNumeroGuia());
        dto.setTransportista(despacho.getTransportista());
        dto.setCostoDespacho(despacho.getCostoDespacho());


        return dto;
    }

    public Despacho toEntity(DespachoDTO dto) {
        if (dto == null) {
            return null;
        }

        Despacho despacho = new Despacho();
        despacho.setDespachoId(dto.getId());
        despacho.setFechaDespacho(dto.getFechaDespacho());
        despacho.setFechaEntregaEstimada(dto.getFechaEntregaEstimada());
        despacho.setFechaEntregaReal(dto.getFechaEntregaReal());
        despacho.setNumeroGuia(dto.getNumeroGuia());
        despacho.setTransportista(dto.getTransportista());
        despacho.setCostoDespacho(dto.getCostoDespacho());

        return despacho;
    }
}
