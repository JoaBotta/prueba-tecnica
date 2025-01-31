package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.PuntoDeVenta.PuntoDeVentaRepository;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.Usuario.UsuarioRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VentaEntradaService {

    @Autowired
    private VentaEntradaRepository ventaEntradaRepository;

    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    // ✅ Crear una nueva venta de entrada con QR
    public VentaEntradaResponseDTO createVentaEntrada(VentaEntradaRequestDTO requestDTO) {
        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(requestDTO.getPuntoVentaId())
                .orElseThrow(() -> new RuntimeException("Punto de Venta no encontrado"));

        Usuario empleadoVentas = usuarioRepository.findById(requestDTO.getEmpleadoVentasId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        VentaEntrada ventaEntrada = new VentaEntrada(puntoDeVenta, empleadoVentas, formaDePago);
        ventaEntrada.setFecha(LocalDateTime.now());
        ventaEntrada.setQrCode(UUID.randomUUID().toString()); // Generar QR único

        ventaEntradaRepository.save(ventaEntrada);

        return mapToDTO(ventaEntrada);
    }

    // ✅ Obtener todas las ventas de entrada
    public List<VentaEntradaResponseDTO> getAllVentasEntrada() {
        return ventaEntradaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener una venta de entrada por ID
    public VentaEntradaResponseDTO getVentaEntradaById(Long id) {
        return ventaEntradaRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Venta de entrada no encontrada"));
    }

    // ✅ Obtener todas las ventas de un Punto de Venta específico
    public List<VentaEntradaResponseDTO> getVentasByPuntoDeVenta(Long puntoVentaId) {
        return ventaEntradaRepository.findByPuntoDeVentaId(puntoVentaId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Eliminar una venta de entrada
    public void deleteVentaEntrada(Long id) {
        ventaEntradaRepository.deleteById(id);
    }

    // ✅ Método para convertir VentaEntrada en un DTO
    private VentaEntradaResponseDTO mapToDTO(VentaEntrada ventaEntrada) {
        return new VentaEntradaResponseDTO(
                ventaEntrada.getId(),
                ventaEntrada.getPuntoDeVenta().getNombre(),
                ventaEntrada.getEmpleadoVentas().getUsername(),
                ventaEntrada.getFormaDePago().getNombre(),
                ventaEntrada.getTotal(),
                ventaEntrada.getFecha(),
                ventaEntrada.getQrCode(),
                null
        );
    }
}
