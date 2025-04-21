package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marcaId;
    @Column( nullable = false)
    private String nombre;
    private String logoUrl;

    @OneToMany(mappedBy = "marca")
    private List<Producto> productos = new ArrayList<>();


    public Marca(Long id, String nombre, String logoUrl, List<Producto> productos) {
        this.marcaId = id;
        this.nombre = nombre;
        this.logoUrl = logoUrl;
        this.productos = productos;
    }

    public Marca() {
    }


    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

