package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.ComunaDTO;
import cl.Ferramas.Ferramas.entity.Comuna;
import org.springframework.stereotype.Component;


@Component
public class ComunaMapper {



    public ComunaDTO ComunatoDTO(Comuna comuna){

        ComunaDTO comunaDTO = new ComunaDTO();

        comunaDTO.setId(comuna.getId());
        comunaDTO.setNombre(comuna.getNombre());
        comunaDTO.setIdCiudad(comuna.getCiudad().getId());


        return comunaDTO;
    }

    public Comuna DTOtoComuna(ComunaDTO comunadto){

        Comuna comuna = new Comuna();

        comuna.setId(comunadto.getId());
        comuna.setNombre(comunadto.getNombre());


        return comuna;
    }




}
