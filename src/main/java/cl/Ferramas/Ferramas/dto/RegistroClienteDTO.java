package cl.Ferramas.Ferramas.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RegistroClienteDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 3, max = 50, message = "El apellido paterno debe tener entre 3 y 50 caracteres")
    private String apellidop;

    @NotBlank(message = "El apellido meterno es obligatorio")
    @Size(min = 3, max = 50, message = "El apellido materno debe tener entre 3 y 50 caracteres")
    private String apellidom;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números")
    private String telefono;

    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;

    private LocalDate fechaRegistro;

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^\\d{1,8}-[0-9kK]{1}$", message = "Formato de RUT incorrecto (ej: 12345678-9)")
    private String rut;

    @NotNull(message = "La comuna no puede ser nulo")
    private Long comuna;

    @NotNull(message = "El rol no puede ser nulo")
    private Long rol;

    public RegistroClienteDTO() {
    }

    public RegistroClienteDTO(Long id, String nombre, String apellidop, String apellidom, String email, String password, String direccion, String telefono, LocalDate fechaNacimiento, LocalDate fechaRegistro, String rut, Long comuna, Long rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.rut = rut;
        this.comuna = comuna;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Long getComuna() {
        return comuna;
    }

    public void setComuna(Long comuna) {
        this.comuna = comuna;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }
}
