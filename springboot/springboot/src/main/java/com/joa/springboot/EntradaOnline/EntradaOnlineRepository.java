package com.joa.springboot.EntradaOnline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntradaOnlineRepository extends JpaRepository<EntradaOnline, Long> {
    @Modifying
    @Query("update EntradaOnline e set e.stock = e.stock - 1 where e.id = :id and e.stock > 0")
    int reservarUnidad(@Param("id") Long id);
}