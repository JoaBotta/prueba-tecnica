package com.joa.springboot.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteDTO dto) {
        ClienteResponseDTO cliente = clienteService.crearCliente(dto);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(
            @PathVariable Long id,
            @RequestBody ClienteDTO dto) {
        ClienteResponseDTO cliente = clienteService.actualizarCliente(id, dto);
        return ResponseEntity.ok(cliente);
    }


    // Registrar una asistencia para el cliente (crear historial + actualizar estado actual)
    @PutMapping("/{clienteId}/asistencia")
    public void actualizarHistorialAsistencia(
            @PathVariable Long clienteId,
            @RequestParam Long estadoId
    ) {
        clienteService.actualizarHistorialAsistencia(clienteId, estadoId);
    }

    // Cambiar directamente el estado actual del cliente SIN registrar historial
    @PutMapping("/{clienteId}/estado")
    public void cambiarEstadoCliente(
            @PathVariable Long clienteId,
            @RequestParam Long nuevoEstadoId
    ) {
        clienteService.cambiarEstadoCliente(clienteId, nuevoEstadoId);
    }
    @GetMapping("/{clienteId}/asistencias/presente")
    public long contarPresencias(@PathVariable Long clienteId) {
        return clienteService.contarAsistenciasPresente(clienteId);
    }

}
