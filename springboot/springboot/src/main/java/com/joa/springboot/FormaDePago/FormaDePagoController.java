package com.joa.springboot.FormaDePago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formas-de-pago")
public class FormaDePagoController {

    @Autowired
    private FormaDePagoService formaDePagoService;

    @PostMapping
    public ResponseEntity<FormaDePagoResponseDTO> createFormaDePago(@RequestBody FormaDePagoRequestDTO requestDTO) {
        FormaDePagoResponseDTO responseDTO = formaDePagoService.createFormaDePago(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<FormaDePagoResponseDTO>> getAllFormasDePago() {
        List<FormaDePagoResponseDTO> responseDTOs = formaDePagoService.getAllFormasDePago();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagoResponseDTO> getFormaDePagoById(@PathVariable Long id) {
        FormaDePagoResponseDTO responseDTO = formaDePagoService.getFormaDePagoById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagoResponseDTO> updateFormaDePago(@PathVariable Long id, @RequestBody FormaDePagoRequestDTO requestDTO) {
        FormaDePagoResponseDTO responseDTO = formaDePagoService.updateFormaDePago(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormaDePago(@PathVariable Long id) {
        formaDePagoService.deleteFormaDePago(id);
        return ResponseEntity.noContent().build();
    }
}

