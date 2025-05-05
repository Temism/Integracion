package cl.Ferramas.Ferramas.dto;



import java.time.LocalDate;

public class ClienteDTO {

    private Long Id;
    private String rut;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String email;
    private String password;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    private LocalDate fechaRegistro;
    private String comuna;
    private Long rol;
    private Boolean activo;


    public ClienteDTO(Long Id,String nombre,String password,Boolean activo, String apellidop, String apellidom, String email, String direccion, String telefono, LocalDate fechaNacimiento, LocalDate fechaRegistro, String rut, String comuna, Long rol) {
        this.Id = Id;
        this.nombre = nombre;
        this.password = password;
        this.activo = activo;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.rut = rut;
        this.comuna = comuna;
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }

    public ClienteDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
}
