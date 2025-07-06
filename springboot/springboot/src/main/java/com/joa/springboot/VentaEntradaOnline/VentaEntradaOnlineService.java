package com.joa.springboot.VentaEntradaOnline;

import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.EntradaOnline.EntradaOnlineRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.QR.EntradaQR;
import com.joa.springboot.QR.EntradaQRRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Boliche.BolicheRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class VentaEntradaOnlineService {

    @Autowired
    private VentaEntradaOnlineRepository ventaEntradaOnlineRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private EntradaOnlineRepository entradaOnlineRepository;

    @Autowired
    private EntradaQRRepository entradaQRRepository;


    @Autowired
    private BolicheRepository bolicheRepository;

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;


    public VentaEntradaOnlineResponseDTO crearVentaEntradaOnline(VentaEntradaOnlineRequestDTO dto) {
        Boliche boliche = bolicheRepository.findById(dto.getBolicheId())
            .orElseThrow(() -> new RuntimeException("Boliche no encontrado"));

        FormaDePago formaDePago = formaDePagoRepository.findById(dto.getFormaDePagoId())
                .orElseThrow(() -> new IllegalArgumentException("Forma de Pago no encontrada"));

        // 1️⃣ Crear y guardar la venta
        VentaEntradaOnline venta = new VentaEntradaOnline();
        venta.setBoliche(boliche);
        venta.setFormaDePago(formaDePago);
        venta.setFechaHora(dto.getFechaHora());
        venta.setNombreComprador(dto.getNombreComprador());
        venta.setCorreoElectronico(dto.getCorreoElectronico());
        venta.setTelefono(dto.getTelefono());

        venta = ventaEntradaOnlineRepository.save(venta); // Necesitamos el ID de la venta

        // 2️⃣ Crear y guardar los detalles (individualmente, para obtener sus IDs)
        List<DetalleVentaEntrada> detalles = new ArrayList<>();
        for (var detalleDto : dto.getDetalleVentaEntrada()) {
            EntradaOnline entradaOnline = entradaOnlineRepository.findById(detalleDto.getEntradaId())
                    .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

            DetalleVentaEntrada detalle = new DetalleVentaEntrada();
            detalle.setVentaEntradaOnline(venta);
            detalle.setEntradaOnline(entradaOnline);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setSubtotal(detalleDto.getCantidad() * entradaOnline.getPrecio().doubleValue());

            // 🔹 Guardar individualmente el detalle
            detalle = detalleVentaEntradaRepository.save(detalle);
            detalles.add(detalle);

            // 3️⃣ Por cada unidad comprada, generar un QR vinculado al detalle ya persistido
            for (int i = 0; i < detalle.getCantidad(); i++) {
                EntradaQR qr = new EntradaQR();
                qr.setDetalleVentaEntrada(detalle);
                String contenidoQR = "VENTA-" + venta.getId() + "-DETALLE-" + detalle.getId() + "-NRO-" + (i + 1);
                qr.setTextoQR(contenidoQR);
                entradaQRRepository.save(qr);
            }
        }

        // 4️⃣ Asociar los detalles a la venta y actualizar total
        venta.setDetalleVentaEntrada(detalles);
        venta.setTotalPrecio(detalles.stream().mapToDouble(DetalleVentaEntrada::getSubtotal).sum());
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

    private VentaEntradaOnlineResponseDTO mapToDTO(VentaEntradaOnline venta) {
        VentaEntradaOnlineResponseDTO responseDTO = new VentaEntradaOnlineResponseDTO();
        responseDTO.setId(venta.getId());
        responseDTO.setFormaDePagoId(venta.getFormaDePago().getId());
        responseDTO.setFechaHora(venta.getFechaHora());
        responseDTO.setTotalPrecio(venta.getTotalPrecio());
        responseDTO.setNombreComprador(venta.getNombreComprador());
        responseDTO.setCorreoElectronico(venta.getCorreoElectronico());
        responseDTO.setTelefono(venta.getTelefono());
        responseDTO.setBolicheNombre(venta.getBoliche().getNombre()); // si querés el nombre del boliche

        // 🔹 Agregá esta parte para mapear los detalles
        List<DetalleVentaEntradaResponseDTO> detallesDTO = venta.getDetalleVentaEntrada().stream().map(detalle -> {
            DetalleVentaEntradaResponseDTO dto = new DetalleVentaEntradaResponseDTO();
            dto.setId(detalle.getId());
            dto.setEntradaId(detalle.getEntradaOnline().getId());
            dto.setCantidad(detalle.getCantidad());
            dto.setSubtotal(detalle.getSubtotal());

            // 🔹 Obtener los QR desde base de datos
            List<EntradaQR> codigos = entradaQRRepository.findByDetalleVentaEntradaId(detalle.getId());

            // 🔹 Convertir a URLs
            List<String> qrUrls = codigos.stream()
                    .map(qr -> "http://localhost:8080/v1/qrcode?text=" + qr.getTextoQR())
                    .collect(Collectors.toList());

            dto.setCodigosQR(qrUrls);

            return dto;
        }).collect(Collectors.toList());

        responseDTO.setDetalleVentaEntrada(detallesDTO);

        return responseDTO;
    }

}
