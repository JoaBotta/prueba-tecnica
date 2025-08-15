package com.joa.springboot.VentaEntradaFisica;

import com.joa.springboot.DetalleVentaEntradaFisica.DetalleVentaEntradaFisica;
import com.joa.springboot.DetalleVentaEntradaFisica.DetalleVentaEntradaFisicaRepository;
import com.joa.springboot.DetalleVentaEntradaFisica.DetalleVentaEntradaFisicaResponseDTO;
import com.joa.springboot.EntradaFisica.EntradaFisica;
import com.joa.springboot.EntradaFisica.EntradaFisicaRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.PuntoDeVenta.PuntoDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal; // 🔹 Importar BigDecimal para evitar errores de tipo
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class VentaEntradaFisicaService {

    @Autowired
    private VentaEntradaFisicaRepository VentaEntradaRepository;

    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private EntradaFisicaRepository entradaRepository;

    @Autowired
    private DetalleVentaEntradaFisicaRepository detalleVentaFisicaRepository;

    public VentaEntradaFisicaResponseDTO crearVentaEntrada(VentaEntradaFisicaRequestDTO dto) {
        // Buscar entidades relacionadas
        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(dto.getPuntoDeVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Punto de Venta no encontrado"));
    
        FormaDePago formaDePago = formaDePagoRepository.findById(dto.getFormaDePagoId())
                .orElseThrow(() -> new IllegalArgumentException("Forma de Pago no encontrado"));
    
        // Crear la venta física
        final VentaEntradaFisica nuevaVenta = new VentaEntradaFisica(); // 🔹 Se usa 'final' aquí
        nuevaVenta.setPuntoDeVenta(puntoDeVenta);
        nuevaVenta.setFormaDePago(formaDePago);
        nuevaVenta.setFechaHora(dto.getFechaHora());
        nuevaVenta.setTotalPrecio(dto.getTotalPrecio());
    
        // Guardar la venta primero para generar su ID
        VentaEntradaFisica ventaGuardada = VentaEntradaRepository.save(nuevaVenta);

        // Variable para acumular el total
        AtomicReference<Double> totalPrecio = new AtomicReference<>(0.0);
    
        // Crear detalles de la venta
        List<DetalleVentaEntradaFisica> detalles = dto.getDetalles().stream().map(detalleDto -> {
            EntradaFisica entrada = entradaRepository.findById(detalleDto.getEntradaFisicaId())
                    .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));
    
            DetalleVentaEntradaFisica detalle = new DetalleVentaEntradaFisica();
            detalle.setEntradaFisicaId(entrada);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setVentaEntrada(ventaGuardada);
    
            // 🔹 Conversión BigDecimal -> Double
            BigDecimal subtotal = entrada.getPrecio().multiply(BigDecimal.valueOf(detalleDto.getCantidad()));
            detalle.setSubtotal(subtotal.doubleValue());

            // Acumular en totalPrecio
            totalPrecio.updateAndGet(v -> v + subtotal.doubleValue());
    
            return detalle;
        }).collect(Collectors.toList());
    
        // Guardar los detalles
        detalleVentaFisicaRepository.saveAll(detalles);
    
        // Asociar los detalles a la venta y guardar nuevamente
        ventaGuardada.setDetalles(detalles);
        ventaGuardada.setTotalPrecio(totalPrecio.get());
        VentaEntradaRepository.save(ventaGuardada);
    
        return mapToDTO(ventaGuardada);
    }
    

    public List<VentaEntradaFisicaResponseDTO> obtenerTodas() {
        return VentaEntradaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public VentaEntradaFisicaResponseDTO obtenerPorId(Long id) {
        VentaEntradaFisica venta = VentaEntradaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        return mapToDTO(venta);
    }

    private VentaEntradaFisicaResponseDTO mapToDTO(VentaEntradaFisica venta) {
        VentaEntradaFisicaResponseDTO responseDTO = new VentaEntradaFisicaResponseDTO();
        responseDTO.setId(venta.getId());
        responseDTO.setPuntoDeVentaId(venta.getPuntoDeVenta().getId());
        responseDTO.setFormaDePagoId(venta.getFormaDePago().getId());
        responseDTO.setFechaHora(venta.getFechaHora());
        responseDTO.setTotalPrecio(venta.getTotalPrecio());

        // 🔹 Solución al error de constructor en DetalleVentaFisicaResponseDTO
        responseDTO.setDetalles(venta.getDetalles().stream().map(detalle -> {
            DetalleVentaEntradaFisicaResponseDTO detalleDTO = new DetalleVentaEntradaFisicaResponseDTO();
            detalleDTO.setId(detalle.getId());
            detalleDTO.setEntradaFisicaId(detalle.getEntradaFisicaId().getId());
            detalleDTO.setCantidad(detalle.getCantidad());
            detalleDTO.setSubtotal(detalle.getSubtotal());
            return detalleDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
