package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ciudades")
public class Ciudad  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ciudadId;

    @Column(nullable = false)

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<Comuna> comunas = new ArrayList<>();


    public Ciudad(Long ciudadId, String nombre, Region region, List<Comuna> comunas) {
        this.ciudadId = ciudadId;
        this.nombre = nombre;
        this.region = region;
        this.comunas = comunas;

    }

    public Ciudad() {
    }

    public Long getId() {
        return ciudadId;
    }

    public void setId(Long id) {
        this.ciudadId = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Comuna> getComunas() {
        return comunas;
    }

}
