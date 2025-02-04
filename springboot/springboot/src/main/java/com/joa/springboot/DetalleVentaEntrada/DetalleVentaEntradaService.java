package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.VIP.VIP;
import com.joa.springboot.VIP.VIPRepository;
import com.joa.springboot.Entrada.EntradaRepository;
import com.joa.springboot.VentaEntrada.VentaEntrada;
import com.joa.springboot.VentaEntrada.VentaEntradaRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaEntradaService {

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

        @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private VIPRepository VIPRepository;

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    // ✅ Crear un detalle de venta de entrada
    public DetalleVentaEntradaResponseDTO createDetalleVentaEntrada(DetalleVentaEntradaRequestDTO requestDTO) {
        VentaEntrada ventaEntrada = ventaEntradaRepository.findById(requestDTO.getVentaEntradaId())
                .orElseThrow(() -> new RuntimeException("VentaEntrada no encontrada con ID: " + requestDTO.getVentaEntradaId()));

        Entrada entrada = entradaRepository.findById(requestDTO.getEntradaId())
                .orElseThrow(() -> new RuntimeException("Entrada no encontrado con ID: " + requestDTO.getEntradaId()));

        VIP VIP = VIPRepository.findById(requestDTO.getEntradaId())
                .orElseThrow(() -> new RuntimeException("VIP no encontrado con ID: " + requestDTO.getVIPId()));

        DetalleVentaEntrada detalleVentaEntrada = new DetalleVentaEntrada(ventaEntrada, entrada, VIP, requestDTO.getCantidad());
        detalleVentaEntrada = detalleVentaEntradaRepository.save(detalleVentaEntrada);

        return new DetalleVentaEntradaResponseDTO(
                detalleVentaEntrada.getId(),
                entrada.getNombre(),
                VIP.getQrCode(),
                detalleVentaEntrada.getCantidad(),
                detalleVentaEntrada.getSubTotal()
        );
    }

    public List<DetalleVentaEntradaResponseDTO> getAllDetallesVentaEntrada() {
        return detalleVentaEntradaRepository.findAll().stream()
                .map(detalle -> new DetalleVentaEntradaResponseDTO(
                        detalle.getId(),
                        detalle.getEntrada().getNombre(),
                        detalle.getVIP().getQrCode(),
                        detalle.getCantidad(),
                        detalle.getSubTotal()))
                .collect(Collectors.toList());
    }

    public DetalleVentaEntradaResponseDTO getDetalleVentaEntradaById(Long id) {
        DetalleVentaEntrada detalle = detalleVentaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Venta no encontrado con ID: " + id));

        return new DetalleVentaEntradaResponseDTO(
                detalle.getId(),
                detalle.getEntrada().getNombre(),
                detalle.getVIP().getQrCode(),
                detalle.getCantidad(),
                detalle.getSubTotal()
        );
    }

    public void deleteDetalleVentaEntrada(Long id) {
        detalleVentaEntradaRepository.deleteById(id);
    }

    public List<DetalleVentaEntradaResponseDTO> getDetallesByVentaEntrada(Long ventaEntradaId) {
        return detalleVentaEntradaRepository.findDetallesByVentaEntrada(ventaEntradaId).stream()
                .map(detalle -> new DetalleVentaEntradaResponseDTO(
                        detalle.getId(),
                        detalle.getEntrada().getNombre(),
                        detalle.getVIP().getQrCode(),
                        detalle.getCantidad(),
                        detalle.getSubTotal()))
                .collect(Collectors.toList());
    }
}
