package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.Region;

import cl.Ferramas.Ferramas.repository.RegionRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRep regionRep;

    public List<Region> ListarRegiones(){
        return regionRep.findAll();
    }

    public Optional<Region> BuscarRegionPorId(Long regionId){
        return regionRep.findById(regionId);
    }

    public Region guardarRegion(Region region) {
        return regionRep.save(region);
    }

    public void EliminarRegion(Long regionId) {
        regionRep.deleteById(regionId);
    }
}