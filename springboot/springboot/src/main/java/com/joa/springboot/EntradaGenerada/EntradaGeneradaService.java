package com.joa.springboot.EntradaGenerada;

import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntradaGeneradaService {

    @Autowired
    private EntradaGeneradaRepository entradaGeneradaRepository;

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

    public EntradaGeneradaResponseDTO crearEntradaGenerada(EntradaGeneradaRequestDTO dto) {
        Optional<DetalleVentaEntrada> detalleOpt = detalleVentaEntradaRepository.findById(dto.getDetalleVentaEntradaId());
        if (detalleOpt.isEmpty()) {
            throw new IllegalArgumentException("Detalle de venta no encontrado");
        }

        // Crear entrada generada
        EntradaGenerada entrada = new EntradaGenerada();
        entrada.setDetalleVentaEntrada(detalleOpt.get());
        entrada.setQrCode(dto.getQrCode());
        entrada.setUsada(false);

        EntradaGenerada entradaGuardada = entradaGeneradaRepository.save(entrada);

        // Mapear respuesta
        EntradaGeneradaResponseDTO responseDTO = new EntradaGeneradaResponseDTO();
        responseDTO.setId(entradaGuardada.getId());
        responseDTO.setQrCode(entradaGuardada.getQrCode());
        responseDTO.setUsada(entradaGuardada.getUsada());

        return responseDTO;
    }

    public EntradaGeneradaResponseDTO validarEntradaPorQR(String qrCode) {
        EntradaGenerada entrada = entradaGeneradaRepository.findByQrCode(qrCode);
        if (entrada == null) {
            throw new IllegalArgumentException("QR inválido o no existe.");
        }
        if (entrada.getUsada()) {
            throw new IllegalArgumentException("Entrada ya usada.");
        }

        // Marcar como usada
        entrada.setUsada(true);
        entradaGeneradaRepository.save(entrada);

        // Mapear respuesta
        EntradaGeneradaResponseDTO responseDTO = new EntradaGeneradaResponseDTO();
        responseDTO.setId(entrada.getId());
        responseDTO.setQrCode(entrada.getQrCode());
        responseDTO.setUsada(true);

        return responseDTO;
    }
}