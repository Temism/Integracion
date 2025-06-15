package cl.Ferramas.Ferramas.mapper;

import cl.Ferramas.Ferramas.dto.CategoriaDTO;
import cl.Ferramas.Ferramas.entity.Categoria;

public class CategoriaMapper {

    public static CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;
        return new CategoriaDTO(
                categoria.getCategoriaId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }

    public static Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(dto.getId());
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return categoria;
    }
}
