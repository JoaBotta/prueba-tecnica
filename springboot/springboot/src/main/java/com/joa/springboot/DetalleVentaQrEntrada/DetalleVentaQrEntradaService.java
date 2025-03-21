package com.joa.springboot.DetalleVentaQrEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.QrEntrada.QrEntrada;
import com.joa.springboot.QrEntrada.QrEntradaRepository;
import com.joa.springboot.VentaEntrada.VentaEntrada;
import com.joa.springboot.VentaEntrada.VentaEntradaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DetalleVentaQrEntradaService {

    @Autowired
    private DetalleVentaQrEntradaRepository detalleVentaQrEntradaRepository;

    @Autowired
    private QrEntradaRepository qrEntradaRepository;

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    public DetalleVentaQrEntradaResponseDTO createDetalleVentaQrEntrada(DetalleVentaQrEntradaRequestDTO requestDTO) {
        VentaEntrada ventaEntrada = ventaEntradaRepository.findById(requestDTO.getVentaEntradaId())
                .orElseThrow(() -> new RuntimeException("VentaEntrada no encontrada con ID: " + requestDTO.getVentaEntradaId()));

        QrEntrada qrEntrada = qrEntradaRepository.findById(requestDTO.getQrEntradaId())
                .orElseThrow(() -> new RuntimeException("QrEntrada no encontrada con ID: " + requestDTO.getQrEntradaId()));

        // Generar códigos QR
        List<String> codigosQr = IntStream.range(0, requestDTO.getCantidad())
                .mapToObj(i -> UUID.randomUUID().toString())
                .collect(Collectors.toList());

        DetalleVentaQrEntrada detalle = new DetalleVentaQrEntrada(
                ventaEntrada,
                qrEntrada,
                requestDTO.getCantidad(),
                codigosQr
        );

        detalle = detalleVentaQrEntradaRepository.save(detalle);

        return new DetalleVentaQrEntradaResponseDTO(
                detalle.getId(),
                qrEntrada.getNombre(),
                detalle.getCantidad(),
                detalle.getSubTotal(),
                codigosQr
        );
    }

    public List<DetalleVentaQrEntradaResponseDTO> getAllDetallesVentaQrEntrada() {
        return detalleVentaQrEntradaRepository.findAll().stream()
                .map(detalle -> new DetalleVentaQrEntradaResponseDTO(
                        detalle.getId(),
                        detalle.getQrEntrada().getNombre(),
                        detalle.getCantidad(),
                        detalle.getSubTotal(),
                        detalle.getCodigosQr()))
                .collect(Collectors.toList());
    }

    public DetalleVentaQrEntradaResponseDTO getDetalleVentaQrEntradaById(Long id) {
        DetalleVentaQrEntrada detalle = detalleVentaQrEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Venta QR no encontrado con ID: " + id));

        return new DetalleVentaQrEntradaResponseDTO(
                detalle.getId(),
                detalle.getQrEntrada().getNombre(),
                detalle.getCantidad(),
                detalle.getSubTotal(),
                detalle.getCodigosQr()
        );
    }

    public void deleteDetalleVentaQrEntrada(Long id) {
        if (!detalleVentaQrEntradaRepository.existsById(id)) {
            throw new RuntimeException("El detalle de venta QR con ID " + id + " no existe.");
        }
        detalleVentaQrEntradaRepository.deleteById(id);
    }
}
