package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "comuna")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)

    private Long comunaId;
    @Column(nullable = false)
    private String nombre;


    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "comuna", fetch = FetchType.LAZY)
    private List<Direccion> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "comuna", fetch = FetchType.LAZY)
    private List<Sucursal> sucursales = new ArrayList<>();


    public Comuna(Long id, String nombre, Ciudad ciudad, List<Direccion> direcciones, List<Sucursal> sucursales) {
        this.comunaId = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direcciones = direcciones;
        this.sucursales = sucursales;
    }

    public Comuna() {
    }

    public Long getId() {
        return comunaId;
    }

    public void setId(Long id) {
        this.comunaId = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
