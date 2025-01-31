package com.joa.springboot.VentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import com.joa.springboot.Barra.Barra;
import com.joa.springboot.Barra.BarraRepository;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.Usuario.UsuarioRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.Producto.Producto;
import com.joa.springboot.Producto.ProductoRepository;
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarra;
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarraResponseDTO;
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarraRepository;

@Service
public class VentaBarraService {

    @Autowired
    private VentaBarraRepository ventaBarraRepository;

    @Autowired
    private BarraRepository barraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaBarraRepository detalleVentaBarraRepository;

    // ✅ Crear una nueva venta de barra
    public VentaBarraResponseDTO createVentaBarra(VentaBarraRequestDTO requestDTO) {
        // Buscar la barra asociada
        final Barra barra = barraRepository.findById(requestDTO.getBarraId())
                .orElseThrow(() -> new RuntimeException("Barra no encontrada"));

        // Buscar el vendedor
        final Usuario vendedora = usuarioRepository.findById(requestDTO.getVendedoraId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar la forma de pago
        final FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        // Crear la venta de barra sin necesidad de un ID manual
        final VentaBarra ventaBarra = new VentaBarra(barra, vendedora, formaDePago);
        ventaBarra.setFecha(LocalDateTime.now());

        // Guardar la venta en la base de datos antes de asignar los detalles
        VentaBarra nuevaVenta = ventaBarraRepository.save(ventaBarra);

        // Procesar los detalles de la venta y asociarlos a la venta recién creada
        List<DetalleVentaBarra> detalles = requestDTO.getDetalleVenta().stream()
                .map(detalleDTO -> {
                    Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getProductoId()));

                    return new DetalleVentaBarra(nuevaVenta, producto, detalleDTO.getCantidad());
                })
                .collect(Collectors.toList());

        detalleVentaBarraRepository.saveAll(detalles); // Guardar los detalles en la BD
        nuevaVenta.setDetalleVenta(detalles);
        nuevaVenta.calcularTotal();
        ventaBarraRepository.save(nuevaVenta); // Guardar la venta con su total actualizado

        return mapToDTO(nuevaVenta);
    }

    // ✅ Obtener todas las ventas de barra
    public List<VentaBarraResponseDTO> getAllVentasBarra() {
        return ventaBarraRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener una venta específica por ID
    public VentaBarraResponseDTO getVentaById(Long id) {
        return ventaBarraRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // ✅ Obtener ventas por barra específica
    public List<VentaBarraResponseDTO> getVentasByBarra(Long barraId) {
        return ventaBarraRepository.findByBarraId(barraId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Eliminar una venta de barra
    public void deleteVenta(Long id) {
        ventaBarraRepository.deleteById(id);
    }

    // ✅ Método para convertir una VentaBarra en un DTO
    private VentaBarraResponseDTO mapToDTO(VentaBarra ventaBarra) {
        return new VentaBarraResponseDTO(
                ventaBarra.getId(),
                ventaBarra.getBarra().getNombre(),
                ventaBarra.getVendedora().getUsername(),
                ventaBarra.getFormaDePago().getNombre(),
                ventaBarra.getTotal(),
                ventaBarra.getFecha(),
                ventaBarra.getDetalleVenta().stream()
                        .map(detalle -> new DetalleVentaBarraResponseDTO(
                                detalle.getId(),
                                detalle.getProducto().getNombre(),
                                detalle.getCantidad(),
                                detalle.getSubTotal()))
                        .collect(Collectors.toList())
        );
    }
}
