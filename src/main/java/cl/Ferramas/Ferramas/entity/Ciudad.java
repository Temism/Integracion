package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ciudad")
public class Ciudad  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ciudadId;

    @Column(nullable = false)

    private String nombre;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Region region;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comuna> comunas = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
    private List<Direccion> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
    private List<Sucursal> sucursales = new ArrayList<>();


    public Ciudad(Long ciudadId, String nombre, Region region, List<Comuna> comunas, List<Direccion> direcciones, List<Sucursal> sucursales) {
        this.ciudadId = ciudadId;
        this.nombre = nombre;
        this.region = region;
        this.comunas = comunas;
        this.direcciones = direcciones;
        this.sucursales = sucursales;
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

    public void setComunas(List<Comuna> comunas) {
        this.comunas = comunas;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
