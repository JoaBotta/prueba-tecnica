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

    @PostMapping("/lista/{listaId}")
    public ClienteResponseDTO agregarCliente(
            @PathVariable Long listaId,
            @RequestBody ClienteDTO clienteDTO
    ) {
        return clienteService.agregarCliente(listaId, clienteDTO);
    }

    @GetMapping("/lista/{listaId}")
    public List<ClienteResponseDTO> obtenerClientesDeLista(@PathVariable Long listaId) {
        return clienteService.obtenerClientesPorLista(listaId);
    }

    @PutMapping("/{clienteId}/asistencia")
    public void actualizarHistorialAsistencia(
            @PathVariable Long clienteId,
            @RequestParam Boolean asistencia
    ) {
        clienteService.actualizarHistorialAsistencia(clienteId, asistencia);
    }
}
