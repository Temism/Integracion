package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Region;
import cl.Ferramas.Ferramas.entity.Rol;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.RegionRep;
import cl.Ferramas.Ferramas.repository.RolRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRep rolRep;

    public List<Rol> ListarRoles(){
        return rolRep.findAll();
    }

    public Optional<Rol> BuscarRolPorId(Long pagoId){
        return rolRep.findById(pagoId);
    }

    public Rol guardarRol(Rol rol) {
        return rolRep.save(rol);
    }

    public void EliminarRol(Long rolId) {
        rolRep.deleteById(rolId);
    }
}
