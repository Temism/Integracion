package cl.Ferramas.Ferramas.dto;


public class CiudadDTO {

    private Long id;
    private String nombre;
    private Long idRegion;

    public CiudadDTO(Long id, String nombre, Long idRegion) {
        this.id = id;
        this.nombre = nombre;
        this.idRegion = idRegion;
    }

    public CiudadDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }
}
