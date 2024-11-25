package com.joa.springboot.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    
    @Modifying
    @Query("UPDATE Usuario u SET u.firstname = :firstname, u.lastname = :lastname, u.country = :country WHERE u.id = :id")
    void updateUsuario(
        @Param("id") Integer id, 
        @Param("firstname") String firstname, 
        @Param("lastname") String lastname, 
        @Param("country") String country
    );
}
