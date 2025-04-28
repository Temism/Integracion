package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "regiones")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;
    @Column( nullable = false)
    private String nombre;
    private String codigo;
    private String ordinal;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Ciudad> ciudades = new ArrayList<>();


    public Region(Long regionId, String nombre, String codigo, String ordinal, List<Ciudad> ciudades) {
        this.regionId = regionId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.ordinal = ordinal;
        this.ciudades = ciudades;
    }

    public Region() {
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

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}

