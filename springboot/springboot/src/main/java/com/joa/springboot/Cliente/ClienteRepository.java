package com.joa.springboot.Cliente;

import com.joa.springboot.Lista.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByLista(Lista lista);
}
