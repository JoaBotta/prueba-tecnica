package com.joa.springboot.Usuario;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Cambiar de Integer a Long

    @Column(nullable = false, unique = true)
    private String email;

    @Basic
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String lastname;

    private String firstname;

    private String country;

    @Column(nullable = false)
    private String password;



    private LocalDate fechaNacimiento; 

    @Enumerated(EnumType.STRING)
    private Role role; 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setUsername(String username) {
        this.email = username;
    }
    

}