package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.RegionDTO;
import cl.Ferramas.Ferramas.entity.Region;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RegionMapper {

    public RegionDTO toDTO(Region region) {
        if (region == null) {
            return null;
        }
        
        return new RegionDTO(
                region.getRegionId(), 
                region.getNombre(), 
                region.getCodigo(), 
                region.getOrdinal(), 
                region.getCiudades()
                       .stream()
                       .map(ciudad -> ciudad.getNombre()) // o ciudadId
                       .collect(Collectors.toList()) 
        );
    }
}
