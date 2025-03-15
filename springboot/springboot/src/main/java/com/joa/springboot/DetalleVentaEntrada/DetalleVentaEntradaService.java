package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.Entrada.EntradaRepository;
import com.joa.springboot.QrEntrada.QrEntrada;
import com.joa.springboot.QrEntrada.QrEntradaRepository;
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
    private QrEntradaRepository qrEntradaRepository;

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    public DetalleVentaEntradaResponseDTO createDetalleVentaEntrada(DetalleVentaEntradaRequestDTO requestDTO) {
        VentaEntrada ventaEntrada = ventaEntradaRepository.findById(requestDTO.getEntradaId())
                .orElseThrow(() -> new RuntimeException("VentaEntrada no encontrada con ID: " + requestDTO.getEntradaId()));

        Entrada entrada = requestDTO.getEntradaId() != null 
                ? entradaRepository.findById(requestDTO.getEntradaId()).orElse(null)
                : null;

        QrEntrada qrEntrada = requestDTO.getQrEntradaId() != null 
                ? qrEntradaRepository.findById(requestDTO.getQrEntradaId()).orElse(null)
                : null;

        DetalleVentaEntrada detalleVentaEntrada = new DetalleVentaEntrada(ventaEntrada, entrada, qrEntrada, requestDTO.getCantidad());
        detalleVentaEntrada = detalleVentaEntradaRepository.save(detalleVentaEntrada);

        return new DetalleVentaEntradaResponseDTO(
                detalleVentaEntrada.getId(),
                entrada != null ? entrada.getNombre() : qrEntrada.getNombre(),
                detalleVentaEntrada.getCantidad(),
                detalleVentaEntrada.getSubTotal()
        );
    }

    public List<DetalleVentaEntradaResponseDTO> getAllDetallesVentaEntrada() {
        return detalleVentaEntradaRepository.findAll().stream()
                .map(detalle -> new DetalleVentaEntradaResponseDTO(
                        detalle.getId(),
                        detalle.getEntrada() != null ? detalle.getEntrada().getNombre() : detalle.getQr_entrada().getNombre(),
                        detalle.getCantidad(),
                        detalle.getSubTotal()))
                .collect(Collectors.toList());
    }

    public DetalleVentaEntradaResponseDTO getDetalleVentaEntradaById(Long id) {
        DetalleVentaEntrada detalle = detalleVentaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Venta no encontrado con ID: " + id));

        return new DetalleVentaEntradaResponseDTO(
                detalle.getId(),
                detalle.getEntrada() != null ? detalle.getEntrada().getNombre() : detalle.getQr_entrada().getNombre(),
                detalle.getCantidad(),
                detalle.getSubTotal()
        );
    }

    public void deleteDetalleVentaEntrada(Long id) {
        detalleVentaEntradaRepository.deleteById(id);
    }
}
