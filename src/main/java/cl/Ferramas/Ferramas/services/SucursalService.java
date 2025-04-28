package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Sucursal;
import cl.Ferramas.Ferramas.repository.SucursalRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    private final SucursalRep sucursalRepository;


    @Autowired
    public SucursalService(SucursalRep sucursalRepository) {
        this.sucursalRepository = sucursalRepository;

    }

    public List<Sucursal> BuscarTodasSucursales() {
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> BuscarPorId(Long sucursalId) {

        return sucursalRepository.findById(sucursalId);
    }

    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void EliminarSucursal(Long sucursalId) {
        sucursalRepository.deleteById(sucursalId);
    }

    /* public List<Sucursal> findByRegion(Long regionId) {
        return sucursalRepository.findByRegion_RegionId(regionId);
    }

    public List<Sucursal> findByCiudad(Long ciudadId) {
        return sucursalRepository.findByCiudad_CiudadId(ciudadId);
    }

    public List<Sucursal> findByComuna(Long comunaId) {
        return sucursalRepository.findByComuna_ComunaId(comunaId);
    }

    public List<Sucursal> findBodegaCentral() {
        return sucursalRepository.findByEsBodegaCentral(true);
    }*/


}
