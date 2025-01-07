package com.joa.springboot.Barra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/barra")
public class BarraController {
    @Autowired
    private BarraService barraService;

    @PostMapping
    public ResponseEntity<BarraResponseDTO> registrarVenta(@RequestBody BarraRequestDTO barraRequest) {
        Barra barra = Barra.builder()
                .producto(barraRequest.getProducto())
                .cantidad(barraRequest.getCantidad())
                .precioUnitario(barraRequest.getPrecioUnitario())
                .metodoPago(barraRequest.getMetodoPago())
                .numeroTransaccion(barraRequest.getNumeroTransaccion())
                .build();

        Barra barraRegistrada = barraService.registrarVenta(barra);

        BarraResponseDTO responseDTO = convertirABarraResponseDTO(barraRegistrada);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BarraResponseDTO>> listarVentas() {
        List<Barra> barras = barraService.listarVentas();
        List<BarraResponseDTO> responseDTOs = barras.stream()
                .map(this::convertirABarraResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> obtenerTotalVentas() {
        BigDecimal totalVentas = barraService.calcularTotalVentas();
        return ResponseEntity.ok(totalVentas);
    }

    private BarraResponseDTO convertirABarraResponseDTO(Barra barra) {
        BarraResponseDTO responseDTO = new BarraResponseDTO();
        responseDTO.setId(barra.getId());
        responseDTO.setProducto(barra.getProducto());
        responseDTO.setCantidad(barra.getCantidad());
        responseDTO.setPrecioUnitario(barra.getPrecioUnitario());
        responseDTO.setTotal(barra.getPrecioUnitario().multiply(BigDecimal.valueOf(barra.getCantidad())));
        responseDTO.setMetodoPago(barra.getMetodoPago());
        responseDTO.setNumeroTransaccion(barra.getNumeroTransaccion());
        responseDTO.setFechaHora(barra.getFechaHora());
        return responseDTO;
    }
}
