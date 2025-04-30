package cl.Ferramas.Ferramas.repository;


import cl.Ferramas.Ferramas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRep extends JpaRepository<Usuario,Long>  {

    @Query(value = "SELECT * FROM usuario WHERE email = :email", nativeQuery = true)
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM usuario WHERE rol_id = :rolId", nativeQuery = true)
    List<Usuario> findByRolId(@Param("rolId") Long rolId);

    @Query(value = "SELECT * FROM usuario WHERE sucursal_id = :sucursalId", nativeQuery = true)
    List<Usuario> findBySucursalId(@Param("sucursalId") Long sucursalId);

    @Query(value = "SELECT * FROM usuario WHERE rol_id <> 2", nativeQuery = true)
    List<Usuario> findAllExceptRolId2();



}
