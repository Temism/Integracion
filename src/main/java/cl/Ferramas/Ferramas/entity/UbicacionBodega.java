package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ubicacion_bodega")
public class UbicacionBodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ubicacionId;
    @Column(nullable = false)
    private String nombre;
    @Column( nullable = false)
    private String fila;
    @Column( nullable = false)
    private String columna;
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @OneToMany(mappedBy = "ubicacion",fetch = FetchType.LAZY)
    private List<Inventario> inventarios = new ArrayList<>();


    public UbicacionBodega(Long id, String nombre, String fila, String columna, String descripcion, Sucursal sucursal, List<Inventario> inventarios) {
        this.ubicacionId = id;
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.descripcion = descripcion;
        this.sucursal = sucursal;
        this.inventarios = inventarios;
    }

    public UbicacionBodega() {
    }


    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }
}
