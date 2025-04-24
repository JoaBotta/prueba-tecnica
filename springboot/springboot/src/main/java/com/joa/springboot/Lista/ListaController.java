package com.joa.springboot.Lista;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
@CrossOrigin(origins = "*")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @GetMapping
    public List<Lista> obtenerListas() {
        return listaService.obtenerTodasLasListas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> obtenerListaPorId(@PathVariable Long id) {
        return listaService.obtenerListaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lista crearLista(@RequestBody Lista lista) {
        return listaService.crearLista(lista);
    }

    @PutMapping("/{id}")
    public Lista actualizarLista(@PathVariable Long id, @RequestBody Lista lista) {
        return listaService.actualizarLista(id, lista);
    }

    @DeleteMapping("/{id}")
    public void eliminarLista(@PathVariable Long id) {
        listaService.eliminarLista(id);
    }
    @GetMapping("/resumen")
    public List<ListaResumenDTO> obtenerListasResumen() {
        return listaService.obtenerResumenDeListas();
    }

}