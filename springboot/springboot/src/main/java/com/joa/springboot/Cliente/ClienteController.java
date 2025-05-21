package com.joa.springboot.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Crear un nuevo cliente en una lista (requiere estadoId en el ClienteDTO)
    @PostMapping("/lista/{listaId}")
    public ClienteResponseDTO agregarCliente(
            @PathVariable Long listaId,
            @RequestBody ClienteDTO clienteDTO
    ) {
        return clienteService.agregarCliente(listaId, clienteDTO);
    }

    // Obtener todos los clientes de una lista
    @GetMapping("/lista/{listaId}")
    public List<ClienteResponseDTO> obtenerClientesDeLista(@PathVariable Long listaId) {
        return clienteService.obtenerClientesPorLista(listaId);
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
