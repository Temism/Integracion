package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.CiudadDTO;
import cl.Ferramas.Ferramas.entity.Ciudad;
import cl.Ferramas.Ferramas.entity.Region;
import org.springframework.stereotype.Component;

@Component
public class CiudadMapper {

    public CiudadDTO toDTO(Ciudad ciudad) {
        if (ciudad == null) {
            return null;
        }

        CiudadDTO dto = new CiudadDTO();
        dto.setId(ciudad.getId());
        dto.setNombre(ciudad.getNombre());
        dto.setIdRegion(ciudad.getRegion().getRegionId());

        return dto;
    }

    public Ciudad toEntity(CiudadDTO dto) {
        if (dto == null) {
            return null;
        }

        Ciudad ciudad = new Ciudad();

        ciudad.setNombre(dto.getNombre());

        if (dto.getIdRegion() != null) {
            Region region = new Region();
            region.setRegionId(dto.getIdRegion());
            ciudad.setRegion(region);
        }

        return ciudad;
    }
}
