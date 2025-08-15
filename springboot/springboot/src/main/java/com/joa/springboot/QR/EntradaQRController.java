package com.joa.springboot.QR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/qr")
@CrossOrigin("*")
public class EntradaQRController {

    @Autowired
    private EntradaQRRepository entradaQRRepository;

    @GetMapping("/venta/{ventaId}")
    public List<String> obtenerQRPorVenta(@PathVariable Long ventaId) {
        List<EntradaQR> qrs = entradaQRRepository.findByVentaEntradaOnline_Id(ventaId);
        return qrs.stream()
                .map(qr -> "http://localhost:8080/v1/qrcode?text=" + qr.getTextoQR())
                .collect(Collectors.toList());
    }
}
