package com.joa.springboot.QrEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QrEntradaService {

    @Autowired
    private QrEntradaRepository qrEntradaRepository;

    // Crear una nueva QrEntrada con código QR generado automáticamente
    public QrEntradaResponseDTO crearQrEntrada(QrEntradaRequestDTO requestDTO) {
        QrEntrada qrEntrada = new QrEntrada(
                requestDTO.getNombre(),
                requestDTO.getPrecio(),
                requestDTO.getNombreComprador(),
                requestDTO.getCorreoElectronico(),
                requestDTO.getTelefono()
        );
        qrEntrada = qrEntradaRepository.save(qrEntrada);
        return mapToDTO(qrEntrada);
    }

    // Listar todas las QrEntradas
    public List<QrEntradaResponseDTO> listarQrEntrada() {
        return qrEntradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una QrEntrada por ID
    public QrEntradaResponseDTO obtenerQrEntradaPorId(Long id) {
        QrEntrada qrEntrada = qrEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QrEntrada no encontrada con ID: " + id));
        return mapToDTO(qrEntrada);
    }

    // Actualizar una QrEntrada
    public QrEntradaResponseDTO actualizarQrEntrada(Long id, QrEntradaRequestDTO requestDTO) {
        QrEntrada qrEntrada = qrEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QrEntrada no encontrada con ID: " + id));

        qrEntrada.setNombre(requestDTO.getNombre());
        qrEntrada.setPrecio(requestDTO.getPrecio());
        qrEntrada.setNombreComprador(requestDTO.getNombreComprador());
        qrEntrada.setCorreoElectronico(requestDTO.getCorreoElectronico());
        qrEntrada.setTelefono(requestDTO.getTelefono());

        qrEntrada = qrEntradaRepository.save(qrEntrada);
        return mapToDTO(qrEntrada);
    }

    // Eliminar una QrEntrada
    public void eliminarQrEntrada(Long id) {
        qrEntradaRepository.deleteById(id);
    }

    // Método para mapear `QrEntrada` a `QrEntradaResponseDTO`
    private QrEntradaResponseDTO mapToDTO(QrEntrada qrEntrada) {
        return new QrEntradaResponseDTO(
                qrEntrada.getId(),
                qrEntrada.getNombre(),
                qrEntrada.getPrecio(),
                qrEntrada.getNombreComprador(),
                qrEntrada.getCorreoElectronico(),
                qrEntrada.getTelefono(),
                qrEntrada.getCodigoQr()
        );
    }
}
