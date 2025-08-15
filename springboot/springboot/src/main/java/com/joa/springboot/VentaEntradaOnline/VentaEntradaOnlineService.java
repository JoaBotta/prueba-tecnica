package com.joa.springboot.VentaEntradaOnline;

import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.EntradaOnline.EntradaOnlineRepository;
import com.joa.springboot.Evento.Evento;
import com.joa.springboot.Evento.EventoRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.QR.EntradaQR;
import com.joa.springboot.QR.EntradaQRRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class VentaEntradaOnlineService {

    @Autowired
    private VentaEntradaOnlineRepository ventaRepo;

    @Autowired
    private EventoRepository eventoRepo;

    @Autowired
    private FormaDePagoRepository formaDePagoRepo;

    @Autowired
    private EntradaOnlineRepository entradaOnlineRepo;

    @Autowired
    private EntradaQRRepository entradaQRRepository;

    @Transactional
    public VentaEntradaOnlineResponseDTO crearVentaEntradaOnline(VentaEntradaOnlineRequestDTO dto) {
        // 1) Cargar refs requeridas
        Evento boliche = eventoRepo.findById(dto.getBolicheId())
            .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + dto.getBolicheId()));
        FormaDePago formaDePago = formaDePagoRepo.findById(dto.getFormaDePagoId())
            .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada con ID: " + dto.getFormaDePagoId()));
        EntradaOnline entrada = entradaOnlineRepo.findById(dto.getEntradaOnlineId())
            .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + dto.getEntradaOnlineId()));

        // 2) Reservar stock de forma atómica
        int filas = entradaOnlineRepo.reservarUnidad(entrada.getId());
        if (filas == 0) {
            throw new RuntimeException("Stock agotado para esta entrada");
        }

        // 4) Crear venta
        VentaEntradaOnline venta = new VentaEntradaOnline();
        venta.setBoliche(boliche);
        venta.setFormaDePago(formaDePago);
        venta.setFechaHora(dto.getFechaHora() != null ? dto.getFechaHora() : LocalDateTime.now());
        venta.setPrecio(entrada.getPrecio()); // BigDecimal recomendado en la entidad
        venta.setNombreComprador(dto.getNombreComprador());
        venta.setCorreoElectronico(dto.getCorreoElectronico());
        venta.setTelefono(dto.getTelefono());
        venta.setEntradaOnline(entrada);
        venta = ventaRepo.save(venta);

        // 4) Crear QR 1:1 con la venta
        String contenidoQR = "VENTA-" + venta.getId() + "-ENTRADA-" + entrada.getId();
        EntradaQR qr = new EntradaQR();
        qr.setVentaEntradaOnline(venta);
        qr.setTextoQR(contenidoQR);
        entradaQRRepository.save(qr);

        // 5) DTO respuesta
        VentaEntradaOnlineResponseDTO resp = new VentaEntradaOnlineResponseDTO(
            venta.getId(),
            formaDePago.getId(),
            venta.getFechaHora(),
            venta.getPrecio(),
            venta.getNombreComprador(),
            venta.getCorreoElectronico(),
            venta.getTelefono(),
            boliche.getNombre(),
            entrada.getId()
        );
        resp.setCodigosQR(List.of("http://localhost:8080/v1/qrcode?text=" + contenidoQR));
        return resp;
    }

    public List<VentaEntradaOnlineResponseDTO> obtenerTodas() {
        List<VentaEntradaOnline> ventas = ventaRepo.findAll();

        return ventas.stream().map(venta -> {
            VentaEntradaOnlineResponseDTO dto = new VentaEntradaOnlineResponseDTO(
                venta.getId(),
                venta.getFormaDePago().getId(),
                venta.getFechaHora(),
                venta.getPrecio(),
                venta.getNombreComprador(),
                venta.getCorreoElectronico(),
                venta.getTelefono(),
                venta.getBoliche().getNombre(),
                venta.getEntradaOnline().getId()
            );

            // Buscar códigos QR asociados a la venta
            List<EntradaQR> qrs = entradaQRRepository.findByVentaEntradaOnline_Id(venta.getId());
            List<String> codigos = qrs.stream()
                .map(qr -> "http://localhost:8080/v1/qrcode?text=" + qr.getTextoQR())
                .toList();

            dto.setCodigosQR(codigos);
            return dto;
        }).toList();
    }

    public VentaEntradaOnlineResponseDTO obtenerPorId(Long id) {
        // 1. Buscar la venta por ID
        VentaEntradaOnline venta = ventaRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));

        // 2. Crear el DTO de respuesta
        VentaEntradaOnlineResponseDTO dto = new VentaEntradaOnlineResponseDTO(
            venta.getId(),
            venta.getFormaDePago().getId(),
            venta.getFechaHora(),
            venta.getPrecio(),
            venta.getNombreComprador(),
            venta.getCorreoElectronico(),
            venta.getTelefono(),
            venta.getBoliche().getNombre(),
            venta.getEntradaOnline().getId()
        );

        // 3. Buscar y asignar los códigos QR asociados
        List<EntradaQR> qrs = entradaQRRepository.findByVentaEntradaOnline_Id(venta.getId());
        List<String> codigos = qrs.stream()
            .map(qr -> "http://localhost:8080/v1/qrcode?text=" + qr.getTextoQR())
            .toList();

        dto.setCodigosQR(codigos);

        return dto;
    }


}