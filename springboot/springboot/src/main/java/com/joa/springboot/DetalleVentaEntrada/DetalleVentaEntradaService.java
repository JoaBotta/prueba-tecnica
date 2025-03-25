package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joa.springboot.EntradaGenerada.EntradaGenerada;
import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.EntradaOnline.EntradaOnlineRepository;
import com.joa.springboot.VentaEntradaOnline.VentaEntradaOnline;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaEntradaService {

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

    @Autowired
    private EntradaOnlineRepository entradaOnlineRepository;

    public DetalleVentaEntradaResponseDTO crearDetalleVentaEntrada(VentaEntradaOnline venta, DetalleVentaEntradaRequestDTO dto) {
        Optional<EntradaOnline> entradaOpt = entradaOnlineRepository.findById(dto.getEntradaId());
        if (entradaOpt.isEmpty()) {
            throw new IllegalArgumentException("Entrada no encontrada");
        }

        // Crear detalle de venta
        DetalleVentaEntrada detalle = new DetalleVentaEntrada();
        detalle.setVentaEntradaOnline(venta);
        detalle.setEntradaOnline((EntradaOnline) entradaOpt.get());
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());

        // Generar QRs únicos por cada entrada
        List<EntradaGenerada> entradasQR = new ArrayList<>();
        for (int i = 0; i < dto.getCantidad(); i++) {
            EntradaGenerada qr = new EntradaGenerada();
            qr.setDetalleVentaEntrada(detalle);
            qr.setQrCode("VENTA-" + venta.getId() + "-DETALLE-" + detalle.getId() + "-TICKET-" + (i+1));
            qr.setUsada(false);
            entradasQR.add(qr);
        }
        detalle.setEntradasGeneradas(entradasQR);

        DetalleVentaEntrada guardado = detalleVentaEntradaRepository.save(detalle);

        // Mapear respuesta
        DetalleVentaEntradaResponseDTO responseDTO = new DetalleVentaEntradaResponseDTO();
        responseDTO.setId(guardado.getId());
        responseDTO.setEntradaId(guardado.getEntradaOnline().getId());
        responseDTO.setCantidad(guardado.getCantidad());
        responseDTO.setSubtotal(guardado.getSubtotal());

        return responseDTO;
    }
}