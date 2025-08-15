package com.joa.springboot.ListaEvento;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joa.springboot.Cliente.Cliente;

import java.util.List;

@RestController
@RequestMapping("/api/lista")
@CrossOrigin(origins = "*")
public class ListaController {

    private final ListaService ListaService;

    public ListaController(ListaService ListaService) {
        this.ListaService = ListaService;
    }

    @GetMapping
    public List<Lista> obtenerListas() {
        return ListaService.obtenerTodasLasListas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> obtenerListaPorId(@PathVariable Long id) {
        return ListaService.obtenerListaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lista crearLista(@RequestBody Lista lista) {
        return ListaService.crearLista(lista);
    }

    @PutMapping("/{id}")
    public Lista actualizarLista(@PathVariable Long id, @RequestBody Lista lista) {
        return ListaService.actualizarLista(id, lista);
    }

    @DeleteMapping("/{id}")
    public void eliminarLista(@PathVariable Long id) {
        ListaService.eliminarLista(id);
    }

    @GetMapping("/resumen")
    public List<ListaResumenDTO> obtenerListasResumen() {
        return ListaService.obtenerResumenDeListas();
    }

    @PostMapping("/{listaId}/agregarclientes")
    public ResponseEntity<Lista> agregarCliente(
            @PathVariable Long listaId,
            @RequestBody Cliente cliente) {
        return ResponseEntity.ok(ListaService.agregarClienteALaLista(listaId, cliente));
    }

    @GetMapping("/{listaId}/obtenerclientes")
    public ResponseEntity<List<Cliente>> obtenerClientesDeLista(@PathVariable Long listaId) {
        return ResponseEntity.ok(ListaService.obtenerClientesDeLista(listaId));
    }

    @DeleteMapping("/{listaId}/eliminartodoslosclientes")
    public ResponseEntity<Void> eliminarTodosLosClientes(@PathVariable Long listaId) {
        ListaService.eliminarTodosLosClientesDeLista(listaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{listaId}/eliminarcliente/{clienteId}")
    public ResponseEntity<Void> eliminarClienteDeLista(
            @PathVariable Long listaId,
            @PathVariable Long clienteId) {
        ListaService.eliminarClienteDeLista(listaId, clienteId);
        return ResponseEntity.noContent().build();
    }


}