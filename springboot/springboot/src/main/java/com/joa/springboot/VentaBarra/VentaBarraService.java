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

    /**
     * Método para crear una nueva VentaBarra.
     * @param requestDTO Objeto con los datos de la venta.
     * @return DTO de respuesta con los detalles de la venta creada.
     */
    public VentaBarraResponseDTO createVentaBarra(VentaBarraRequestDTO requestDTO) {
        // Buscar la barra asociada
        Barra barra = barraRepository.findById(requestDTO.getBarraId())
                .orElseThrow(() -> new RuntimeException("Barra no encontrada"));

        // Buscar el usuario vendedor asociado
        Usuario vendedora = usuarioRepository.findById(requestDTO.getVendedoraId().intValue())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar la forma de pago asociada
        FormaDePago formaDePago = formaDePagoRepository.findById(requestDTO.getFormaDePagoId())
                .orElseThrow(() -> new RuntimeException("Forma de Pago no encontrada"));

        // Crear la instancia de VentaBarra
        VentaBarra ventaBarra = new VentaBarra(barra, vendedora, formaDePago);
        final VentaBarra ventaBarraFinal = ventaBarra;

        // Resolver los productos y crear los detalles de venta
        List<DetalleVentaBarra> detalles = requestDTO.getDetalleVenta().stream()
                .map(detalleDTO -> {
                    Producto producto = productoRepository.findById(detalleDTO.getproductoId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getproductoId()));
                    return new DetalleVentaBarra(ventaBarraFinal, producto, detalleDTO.getCantidad());
                })
                .collect(Collectors.toList());

        // Asignar los detalles a la venta y calcular el total
        ventaBarra.setDetalleVenta(detalles);
        ventaBarra.calcularTotal();

        // Guardar la venta en el repositorio
        ventaBarra = ventaBarraRepository.save(ventaBarra);

        // Crear y devolver el DTO de respuesta
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
                                detalle.getSubTotal()))
                        .collect(Collectors.toList())
        );
    }

    /**
     * Método para obtener todas las ventas de barra registradas.
     * @return Lista de DTOs de respuesta con los detalles de las ventas.
     */
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
                                        detalle.getSubTotal()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
