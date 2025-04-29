package com.joa.springboot.VentaEntrada;

import com.joa.springboot.DetalleVentaFisica.DetalleVentaFisica;
import com.joa.springboot.DetalleVentaFisica.DetalleVentaFisicaRepository;
import com.joa.springboot.DetalleVentaFisica.DetalleVentaFisicaResponseDTO;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.Entrada.EntradaRepository;
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
public class VentaEntradaService {

    @Autowired
    private VentaEntradaRepository VentaEntradaRepository;

    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private DetalleVentaFisicaRepository detalleVentaFisicaRepository;

    public VentaEntradaResponseDTO crearVentaEntrada(VentaEntradaRequestDTO dto) {
        // Buscar entidades relacionadas
        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(dto.getPuntoDeVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Punto de Venta no encontrado"));
    
        FormaDePago formaDePago = formaDePagoRepository.findById(dto.getFormaDePagoId())
                .orElseThrow(() -> new IllegalArgumentException("Forma de Pago no encontrado"));
    
        // Crear la venta física
        final VentaEntrada nuevaVenta = new VentaEntrada(); // 🔹 Se usa 'final' aquí
        nuevaVenta.setPuntoDeVenta(puntoDeVenta);
        nuevaVenta.setFormaDePago(formaDePago);
        nuevaVenta.setFechaHora(dto.getFechaHora());
        nuevaVenta.setTotalPrecio(dto.getTotalPrecio());
    
        // Guardar la venta primero para generar su ID
        VentaEntrada ventaGuardada = VentaEntradaRepository.save(nuevaVenta);

        // Variable para acumular el total
        AtomicReference<Double> totalPrecio = new AtomicReference<>(0.0);
    
        // Crear detalles de la venta
        List<DetalleVentaFisica> detalles = dto.getDetalles().stream().map(detalleDto -> {
            Entrada entrada = entradaRepository.findById(detalleDto.getEntradaFisicaId())
                    .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));
    
            DetalleVentaFisica detalle = new DetalleVentaFisica();
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
    

    public List<VentaEntradaResponseDTO> obtenerTodas() {
        return VentaEntradaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public VentaEntradaResponseDTO obtenerPorId(Long id) {
        VentaEntrada venta = VentaEntradaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        return mapToDTO(venta);
    }

    private VentaEntradaResponseDTO mapToDTO(VentaEntrada venta) {
        VentaEntradaResponseDTO responseDTO = new VentaEntradaResponseDTO();
        responseDTO.setId(venta.getId());
        responseDTO.setPuntoDeVentaId(venta.getPuntoDeVenta().getId());
        responseDTO.setFormaDePagoId(venta.getFormaDePago().getId());
        responseDTO.setFechaHora(venta.getFechaHora());
        responseDTO.setTotalPrecio(venta.getTotalPrecio());

        // 🔹 Solución al error de constructor en DetalleVentaFisicaResponseDTO
        responseDTO.setDetalles(venta.getDetalles().stream().map(detalle -> {
            DetalleVentaFisicaResponseDTO detalleDTO = new DetalleVentaFisicaResponseDTO();
            detalleDTO.setId(detalle.getId());
            detalleDTO.setEntradaFisicaId(detalle.getEntradaFisicaId().getId());
            detalleDTO.setCantidad(detalle.getCantidad());
            detalleDTO.setSubtotal(detalle.getSubtotal());
            return detalleDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
