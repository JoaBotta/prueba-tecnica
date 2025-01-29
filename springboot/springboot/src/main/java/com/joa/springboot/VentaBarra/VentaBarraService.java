package com.joa.springboot.VentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public VentaBarraResponseDTO createVentaBarra(VentaBarraRequestDTO requestDTO) {
        final Barra barra = barraRepository.findById(requestDTO.getBarraId())
                .orElseThrow(() -> new RuntimeException("Barra no encontrada"));

        final Usuario vendedora = usuarioRepository.findById(requestDTO.getVendedoraId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        final FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        final VentaBarra ventaBarra = ventaBarraRepository.save(new VentaBarra(barra, vendedora, formaDePago));

        List<DetalleVentaBarra> detalles = requestDTO.getDetalleVenta().stream()
                .map(detalleDTO -> {
                    Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getProductoId()));

                    return new DetalleVentaBarra(ventaBarra, producto, detalleDTO.getCantidad());
                })
                .collect(Collectors.toList());

        ventaBarra.setDetalleVenta(detalles);
        ventaBarra.calcularTotal();
        ventaBarraRepository.save(ventaBarra);

        return new VentaBarraResponseDTO(
                ventaBarra.getId(),
                barra.getNombre(),
                vendedora.getUsername(),
                formaDePago.getNombre(),
                ventaBarra.getTotal(),
                ventaBarra.getFecha(),
                detalles.stream()
                        .map(detalle -> new DetalleVentaBarraResponseDTO(
                                detalle.getId(),
                                detalle.getProducto().getNombre(),
                                detalle.getCantidad(),
                                detalle.getSubTotal().doubleValue()))
                        .collect(Collectors.toList())
        );
    }

    public List<VentaBarraResponseDTO> getAllVentasBarra() {
        return ventaBarraRepository.findAll().stream()
                .map(ventaBarra -> new VentaBarraResponseDTO(
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
                                        detalle.getSubTotal().doubleValue()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<VentaBarraResponseDTO> getVentasByBarra(Long barraId) {
        return ventaBarraRepository.findByBarraId(barraId).stream()
                .map(ventaBarra -> new VentaBarraResponseDTO(
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
                                        detalle.getSubTotal().doubleValue()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public VentaBarraTicketDTO generateTicket(Long ventaId) {
        throw new UnsupportedOperationException("El método 'generateTicket' aún no está implementado.");
    }
}
