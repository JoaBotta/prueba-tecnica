package com.joa.springboot.VentaEntradaOnline;

import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.EntradaOnline.EntradaOnlineRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.PuntoDeVenta.PuntoDeVentaRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;
import com.joa.springboot.EntradaGenerada.EntradaGenerada;
import com.joa.springboot.EntradaGenerada.EntradaGeneradaResponseDTO;
import com.joa.springboot.EntradaGenerada.EntradaGeneradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class VentaEntradaOnlineService {

    @Autowired
    private VentaEntradaOnlineRepository ventaEntradaOnlineRepository;

    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

    @Autowired
    private EntradaOnlineRepository entradaOnlineRepository;

    @Autowired
    private EntradaGeneradaRepository entradaGeneradaRepository;

    public VentaEntradaOnlineResponseDTO crearVentaEntradaOnline(VentaEntradaOnlineRequestDTO dto) {
        // 1️⃣ Buscar entidades relacionadas
        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(dto.getPuntoDeVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Punto de Venta no encontrado"));

        FormaDePago formaDePago = formaDePagoRepository.findById(dto.getFormaDePagoId())
                .orElseThrow(() -> new IllegalArgumentException("Forma de Pago no encontrada"));

        // 2️⃣ Crear y guardar la venta online
        VentaEntradaOnline venta = new VentaEntradaOnline();
        venta.setPuntoDeVenta(puntoDeVenta);
        venta.setFormaDePago(formaDePago);
        venta.setFechaHora(dto.getFechaHora());
        venta.setTotalPrecio(dto.getTotalPrecio());
        venta.setNombreComprador(dto.getNombreComprador());
        venta.setCorreoElectronico(dto.getCorreoElectronico());
        venta.setTelefono(dto.getTelefono());

        venta = ventaEntradaOnlineRepository.save(venta); // 🔹 Asegurar que ya tiene un ID

        // 3️⃣ Ahora guardamos los detalles de la venta y las entradas generadas
        List<DetalleVentaEntrada> detalles = new ArrayList<>();
        for (var detalleDto : dto.getDetalleVentaEntrada()) {
            EntradaOnline entradaOnline = entradaOnlineRepository.findById(detalleDto.getEntradaId())
                    .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

            DetalleVentaEntrada detalle = new DetalleVentaEntrada();
            detalle.setVentaEntradaOnline(venta);
            detalle.setEntradaOnline(entradaOnline);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setSubtotal(detalleDto.getCantidad() * entradaOnline.getPrecio().doubleValue());

            // 🔹 Generamos las entradas individualmente con un QR más informativo
            List<EntradaGenerada> entradasGeneradas = new ArrayList<>();
            for (int i = 0; i < detalleDto.getCantidad(); i++) {
                EntradaGenerada entradaGenerada = new EntradaGenerada();
                entradaGenerada.setDetalleVentaEntrada(detalle);

                // 🔹 Generación del QR con más información
                String qrContent = String.format(
                    "VENTA: %d | ENTRADA: %d | CANTIDAD: %d | CLIENTE: %s",
                    venta.getId(),
                    detalle.getEntradaOnline().getId(),
                    detalle.getCantidad(),
                    venta.getNombreComprador()
                );

                entradaGenerada.setQrCode(qrContent);
                entradasGeneradas.add(entradaGenerada);
            }

            detalle.setEntradasGeneradas(entradasGeneradas);
            detalle = detalleVentaEntradaRepository.save(detalle);
            entradaGeneradaRepository.saveAll(entradasGeneradas); // 🔹 Guardamos las entradas generadas
            detalles.add(detalle);
        }

        // 4️⃣ Asociamos los detalles a la venta y guardamos nuevamente
        venta.setDetalleVentaEntrada(detalles);
        venta.setTotalPrecio(detalles.stream().mapToDouble(DetalleVentaEntrada::getSubtotal).sum()); // 🔹 Sumamos el total
        ventaEntradaOnlineRepository.save(venta);

        return mapToDTO(venta);
    }

    public List<VentaEntradaOnlineResponseDTO> obtenerTodas() {
        List<VentaEntradaOnline> ventas = ventaEntradaOnlineRepository.findAll();
        return ventas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public VentaEntradaOnlineResponseDTO obtenerPorId(Long id) {
        VentaEntradaOnline venta = ventaEntradaOnlineRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        return mapToDTO(venta);
    }

    // 🔹 Mapear `detalleVentaEntrada` en la respuesta incluyendo `entradasGeneradas`
    private VentaEntradaOnlineResponseDTO mapToDTO(VentaEntradaOnline venta) {
        VentaEntradaOnlineResponseDTO responseDTO = new VentaEntradaOnlineResponseDTO();
        responseDTO.setId(venta.getId());
        responseDTO.setPuntoDeVentaId(venta.getPuntoDeVenta().getId());
        responseDTO.setFormaDePagoId(venta.getFormaDePago().getId());
        responseDTO.setFechaHora(venta.getFechaHora());
        responseDTO.setTotalPrecio(venta.getTotalPrecio());
        responseDTO.setNombreComprador(venta.getNombreComprador());
        responseDTO.setCorreoElectronico(venta.getCorreoElectronico());
        responseDTO.setTelefono(venta.getTelefono());

        // 🔹 Mapear los detalles correctamente con `entradasGeneradas`
        if (venta.getDetalleVentaEntrada() != null) {
            List<DetalleVentaEntradaResponseDTO> detallesDTO = venta.getDetalleVentaEntrada()
                .stream()
                .map(detalle -> new DetalleVentaEntradaResponseDTO(
                    detalle.getId(),
                    detalle.getEntradaOnline().getId(),
                    detalle.getCantidad(),
                    detalle.getSubtotal(),
                    detalle.getEntradasGeneradas() != null ?
                        detalle.getEntradasGeneradas()
                            .stream()
                            .map(entrada -> new EntradaGeneradaResponseDTO(entrada.getId(), entrada.getQrCode(), entrada.getUsada()))
                            .collect(Collectors.toList())
                        : new ArrayList<>()
                ))
                .collect(Collectors.toList());

            responseDTO.setDetalleVentaEntrada(detallesDTO);
        }

        return responseDTO;
    }
}
