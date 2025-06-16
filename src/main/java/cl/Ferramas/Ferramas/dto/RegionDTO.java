package cl.Ferramas.Ferramas.dto;

import java.util.List;

public class RegionDTO {

    private Long regionId;
    private String nombre;
    private String codigo;
    private String ordinal;
    private List<String> ciudades; // Aquí solo ponemos nombre o IDs de ciudades, así evitamos ciclo

    public RegionDTO(Long regionId, String nombre, String codigo, String ordinal, List<String> ciudades) {
        this.regionId = regionId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.ordinal = ordinal;
        this.ciudades = ciudades;
    }

    public RegionDTO() {
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(String ordinal) {
        this.ordinal = ordinal;
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<String> ciudades) {
        this.ciudades = ciudades;
    }
}
