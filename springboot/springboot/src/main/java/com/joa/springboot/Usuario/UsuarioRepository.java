package com.joa.springboot.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por username o email
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndRole(String email, Role role);
    Optional<Usuario> findById(Long id);


    // Buscar usuario por DNI
    Optional<Usuario> findByDni(String dni);

    // Buscar usuarios por rol
    List<Usuario> findByRole(Role role);

    // Actualizar información de un usuario específico
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.firstname = :firstname, u.lastname = :lastname, u.country = :country, u.role = :role, u.email = :email WHERE u.id = :id")
    void updateUsuario(
        @Param("id") Long id, 
        @Param("firstname") String firstname, 
        @Param("lastname") String lastname, 
        @Param("country") String country,
        @Param("role") Role role,
        @Param("email") String email
    );

    // Verificar existencia de email o username
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // Eliminar un usuario por ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Usuario u WHERE u.id = :id")
    void deleteUsuarioById(@Param("id") Long id);

    // Contar usuarios por rol
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.role = :role")
    long countByRole(@Param("role") Role role);

    // Buscar usuarios por nombre o apellido
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Usuario> findByNameOrLastname(@Param("name") String name);
}