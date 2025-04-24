package com.joa.springboot.HistorialAsistencia;

import org.springframework.stereotype.Component;

@Component
public class HistorialAsistenciaMapper {

    public HistorialAsistenciaDTO toDTO(HistorialAsistencia historialAsistencia) {
        HistorialAsistenciaDTO dto = new HistorialAsistenciaDTO();
        dto.setId(historialAsistencia.getId());
        dto.setClienteId(historialAsistencia.getCliente().getId());
        dto.setListaId(historialAsistencia.getLista().getId());
        dto.setPresente(historialAsistencia.isPresente());
        return dto;
    }

    public HistorialAsistencia toEntity(HistorialAsistenciaDTO dto) {
        HistorialAsistencia historialAsistencia = new HistorialAsistencia();
        historialAsistencia.setId(dto.getId());
        // Aquí deberías buscar el cliente y la lista por ID si es necesario
        // historialAsistencia.setCliente(cliente);
        // historialAsistencia.setLista(lista);
        historialAsistencia.setPresente(dto.isPresente());
        return historialAsistencia;
    }
}