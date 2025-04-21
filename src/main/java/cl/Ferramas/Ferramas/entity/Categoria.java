package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "categoria")
public class Categoria {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long categoriaId;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;


    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private Set<Producto> productos = new HashSet<>();

    public Categoria(Long categoriaId, String nombre, String descripcion, Set<Producto> productos) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = productos;
    }

    public Categoria() {
    }

    public Long getId() {
        return categoriaId;
    }

    public void setId(Long id) {
        this.categoriaId = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
