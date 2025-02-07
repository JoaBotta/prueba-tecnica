package com.joa.springboot.VentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
import com.joa.springboot.DetalleVentaBarra.DetalleVentaBarraRequestDTO;

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

    public VentaBarraResponseDTO createVentaBarra(VentaBarraRequestDTO requestDTO) {
        final Barra barra = barraRepository.findById(requestDTO.getBarraId())
                .orElseThrow(() -> new RuntimeException("Barra no encontrada"));
        final Usuario vendedora = usuarioRepository.findById(requestDTO.getVendedoraId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        final FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        VentaBarra ventaBarra = new VentaBarra(barra, vendedora, formaDePago);
        ventaBarra.setFecha(LocalDateTime.now());
        ventaBarra = ventaBarraRepository.save(ventaBarra);

        List<DetalleVentaBarra> detalles = new ArrayList<>();
        for (DetalleVentaBarraRequestDTO detalleDTO : requestDTO.getDetalleVenta()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getProductoId()));

            DetalleVentaBarra detalle = new DetalleVentaBarra(ventaBarra, producto, detalleDTO.getCantidad());
            detalles.add(detalle);
        }

        detalleVentaBarraRepository.saveAll(detalles);
        ventaBarra.setDetalleVenta(detalles);
        ventaBarra.calcularTotal();
        ventaBarra = ventaBarraRepository.save(ventaBarra);

        return mapToDTO(ventaBarra);
    }

    public VentaBarraResponseDTO getVentaById(Long id) {
        return ventaBarraRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public VentaBarra getVentaByIdEntity(Long id) {
        return ventaBarraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    // 🔹 Método agregado para solucionar el error en `VentaBarraController`
    public List<VentaBarraResponseDTO> getAllVentasBarra() {
        return ventaBarraRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<VentaBarraResponseDTO> getVentasByBarra(Long barraId) {
        return ventaBarraRepository.findByBarraId(barraId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteVenta(Long id) {
        ventaBarraRepository.deleteById(id);
    }

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
