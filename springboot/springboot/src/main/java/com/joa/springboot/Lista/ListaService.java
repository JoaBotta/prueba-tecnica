package com.joa.springboot.Lista;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListaService {

    private final ListaRepository listaRepository;

    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    public List<Lista> obtenerTodasLasListas() {
        return listaRepository.findAll();
    }

    public Optional<Lista> obtenerListaPorId(Long id) {
        return listaRepository.findById(id);
    }

    public Lista crearLista(Lista lista) {
        return listaRepository.save(lista);
    }

    public void eliminarLista(Long id) {
        listaRepository.deleteById(id);
    }

    public Lista actualizarLista(Long id, Lista listaActualizada) {
        return listaRepository.findById(id)
            .map(lista -> {
                lista.setNombre(listaActualizada.getNombre());
                lista.setFecha(listaActualizada.getFecha());
                lista.setClientes(listaActualizada.getClientes());
                return listaRepository.save(lista);
            })
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));
    }
    public List<ListaResumenDTO> obtenerResumenDeListas() {
    return listaRepository.findAll().stream()
        .map(lista -> new ListaResumenDTO(lista.getId(), lista.getNombre(), lista.getFecha()))
        .collect(Collectors.toList());
}

}