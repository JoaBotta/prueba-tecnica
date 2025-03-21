package com.joa.springboot.QrEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QrEntradaService {

    @Autowired
    private QrEntradaRepository QrEntradaRepository;

    // Crear una nueva QrEntrada
    public QrEntradaResponseDTO crearQrEntrada(QrEntradaRequestDTO requestDTO) {
        QrEntrada QrEntrada = new QrEntrada(requestDTO.getNombre(), requestDTO.getPrecio());
        QrEntrada = QrEntradaRepository.save(QrEntrada);
        return mapToDTO(QrEntrada);
    }

    // Listar todas las QrEntradas
    public List<QrEntradaResponseDTO> listarQrEntrada() {
        return QrEntradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una QrEntrada por ID
    public QrEntradaResponseDTO obtenerQrEntradaPorId(Long id) {
        QrEntrada QrEntrada = QrEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QrEntrada no encontrada con ID: " + id));
        return mapToDTO(QrEntrada);
    }

    // Actualizar una QrEntrada
    public QrEntradaResponseDTO actualizarQrEntrada(Long id, QrEntradaRequestDTO requestDTO) {
        QrEntrada QrEntrada = QrEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QrEntrada no encontrada con ID: " + id));

        QrEntrada.setNombre(requestDTO.getNombre());
        QrEntrada.setPrecio(requestDTO.getPrecio());
        QrEntrada = QrEntradaRepository.save(QrEntrada);

        return mapToDTO(QrEntrada);
    }

    // Eliminar una QrEntrada
    public void eliminarQrEntrada(Long id) {
        QrEntradaRepository.deleteById(id);
    }

    // Método para mapear `QrEntrada` a `QrEntradaResponseDTO`
    private QrEntradaResponseDTO mapToDTO(QrEntrada QrEntrada) {
        return new QrEntradaResponseDTO(QrEntrada.getId(), QrEntrada.getNombre(), QrEntrada.getPrecio());
    }
}
