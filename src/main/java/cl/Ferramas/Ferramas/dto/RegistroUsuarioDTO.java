package cl.Ferramas.Ferramas.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RegistroUsuarioDTO {

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
    private LocalDate ultimoLogin;

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^\\d{1,8}-[0-9kK]{1}$", message = "Formato de RUT incorrecto (ej: 12345678-9)")
    private String rut;

    @NotNull(message = "La comuna no puede ser nulo")
    private Long comuna;

    @NotNull(message = "El rol no puede ser nulo")
    private Long rol;

    @NotNull(message = "La sucursal no puede ser nula")
    private Long sucursal;

    public RegistroUsuarioDTO() {}

    public RegistroUsuarioDTO(String nombre, String apellidop, String apellidom, String email, String password, String direccion, String telefono, LocalDate fechaNacimiento, LocalDate fechaRegistro, LocalDate ultimoLogin, String rut, Long comuna, Long rol, Long sucursal) {
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.ultimoLogin = ultimoLogin;
        this.rut = rut;
        this.comuna = comuna;
        this.rol = rol;
        this.sucursal = sucursal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "La sucursal no puede ser nula") Long getSucursal() {
        return sucursal;
    }

    public void setSucursal(@NotNull(message = "El rol no puede ser nulo") Long sucursal) {
        this.sucursal = sucursal;
    }

    public @NotBlank(message = "El nombre es obligatorio") @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El apellido paterno es obligatorio") @Size(min = 3, max = 50, message = "El apellido paterno debe tener entre 3 y 50 caracteres") String getApellidop() {
        return apellidop;
    }

    public void setApellidop(@NotBlank(message = "El apellido paterno es obligatorio") @Size(min = 3, max = 50, message = "El apellido paterno debe tener entre 3 y 50 caracteres") String apellidop) {
        this.apellidop = apellidop;
    }

    public @NotBlank(message = "El apellido meterno es obligatorio") @Size(min = 3, max = 50, message = "El apellido materno debe tener entre 3 y 50 caracteres") String getApellidom() {
        return apellidom;
    }

    public void setApellidom(@NotBlank(message = "El apellido meterno es obligatorio") @Size(min = 3, max = 50, message = "El apellido materno debe tener entre 3 y 50 caracteres") String apellidom) {
        this.apellidom = apellidom;
    }

    public @NotBlank(message = "El email es obligatorio") @Email(message = "El formato del email no es válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El email es obligatorio") @Email(message = "El formato del email no es válido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password) {
        this.password = password;
    }

    public @NotBlank(message = "La dirección es obligatoria") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank(message = "La dirección es obligatoria") String direccion) {
        this.direccion = direccion;
    }

    public @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números") String telefono) {
        this.telefono = telefono;
    }

    public @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual") LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual") LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDate ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public @NotBlank(message = "El RUT es obligatorio") @Pattern(regexp = "^\\d{1,8}-[0-9kK]{1}$", message = "Formato de RUT incorrecto (ej: 12345678-9)") String getRut() {
        return rut;
    }

    public void setRut(@NotBlank(message = "El RUT es obligatorio") @Pattern(regexp = "^\\d{1,8}-[0-9kK]{1}$", message = "Formato de RUT incorrecto (ej: 12345678-9)") String rut) {
        this.rut = rut;
    }

    public @NotNull(message = "La comuna no puede ser nulo") Long getComuna() {
        return comuna;
    }

    public void setComuna(@NotNull(message = "La comuna no puede ser nulo") Long comuna) {
        this.comuna = comuna;
    }

    public @NotNull(message = "El rol no puede ser nulo") Long getRol() {
        return rol;
    }

    public void setRol(@NotNull(message = "El rol no puede ser nulo") Long rol) {
        this.rol = rol;
    }
}
