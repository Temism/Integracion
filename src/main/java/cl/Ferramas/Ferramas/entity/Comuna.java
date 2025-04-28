package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "comunas")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)

    private Long comunaId;
    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "comuna")
    private List<Sucursal> sucursales = new ArrayList<>();

    @OneToMany(mappedBy = "comuna")
    private List<Usuario> usuarios = new ArrayList<>();


    public Comuna(Long id, String nombre, Ciudad ciudad, List<Sucursal> sucursales) {
        this.comunaId = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
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



    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
