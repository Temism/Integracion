package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.dto.ComunaDTO;
import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.entity.*;

import cl.Ferramas.Ferramas.mapper.ComunaMapper;
import cl.Ferramas.Ferramas.repository.CiudadRep;
import cl.Ferramas.Ferramas.repository.ComunRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComunaService {

    @Autowired
    private ComunRep comunRep;
    @Autowired
    private CiudadRep ciudadRep;
    @Autowired
    private ComunaMapper comunaMapper;

    @Transactional
    public List<ComunaDTO> listaComunas() {
        List<Comuna> productos = comunRep.findAll();
        return productos.stream()
                .map(comunaMapper::ComunatoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ComunaDTO buscarComunaporId(Long id){
        Optional<Comuna> productoOptional = comunRep.findById(id);

        return productoOptional.map(comunaMapper::ComunatoDTO).orElse(null);
    }

    @Transactional
    public ComunaDTO guardarComuna(ComunaDTO comunaDTO) {


        Comuna comuna = comunaMapper.DTOtoComuna(comunaDTO);

        Ciudad ciudad = ciudadRep.findById(comunaDTO.getIdCiudad()).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        comuna.setCiudad(ciudad);

        comuna = comunRep.save(comuna);

        return comunaMapper.ComunatoDTO(comuna);
    }

    public void eliminarComuna(Long comunaId) {
        comunRep.deleteById(comunaId);
    }
}
