package cl.Ferramas.Ferramas.dto;

public class ComunaDTO {
    private Long id;
    private String Nombre;
    private Long idCiudad;


    public ComunaDTO(Long id, String nombre, Long idCiudad) {
        this.id = id;
        Nombre = nombre;
        this.idCiudad = idCiudad;
    }

    public ComunaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }
}
