package com.joa.springboot.ListaEvento;

import org.springframework.stereotype.Service;

import com.joa.springboot.Cliente.Cliente;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListaService {
    private final ListaRepository ListaRepository;

    public ListaService(ListaRepository ListaRepository) {
        this.ListaRepository = ListaRepository;
    }

    public List<Lista> obtenerTodasLasListas() {
        return ListaRepository.findAll();
    }

    public Optional<Lista> obtenerListaPorId(Long id) {
        return ListaRepository.findById(id);
    }

    public Lista crearLista(Lista lista) {
        return ListaRepository.save(lista);
    }

    public void eliminarLista(Long id) {
        ListaRepository.deleteById(id);
    }

    public Lista actualizarLista(Long id, Lista listaActualizada) {
        return ListaRepository.findById(id)
            .map(lista -> {
                lista.setNombre(listaActualizada.getNombre());
                return ListaRepository.save(lista);
            })
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));
    }

    public List<ListaResumenDTO> obtenerResumenDeListas() {
        return ListaRepository.findAll().stream()
            .map(lista -> new ListaResumenDTO(lista.getId(), lista.getNombre(),lista.getFechaHora(), lista.getCantidad()))
            .collect(Collectors.toList());
    }
    
    @Transactional
    public Lista agregarClienteALaLista(Long listaId, Cliente cliente) {
        Lista lista = ListaRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        if (lista.getClientes().size() >= lista.getCantidad()) {
            throw new RuntimeException("La lista ha alcanzado su límite de clientes: " + lista.getCantidad());
        }

        lista.getClientes().add(cliente);
        return ListaRepository.save(lista);
    }

    public List<Cliente> obtenerClientesDeLista(Long listaId) {
        Lista lista = ListaRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));
        
        return lista.getClientes();
    }

    @Transactional
    public void eliminarTodosLosClientesDeLista(Long listaId) {
        Lista lista = ListaRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        lista.getClientes().clear(); // Elimina todos los clientes de la lista (solo la relación)
        ListaRepository.save(lista);
    }

    @Transactional
    public void eliminarClienteDeLista(Long listaId, Long clienteId) {
        Lista lista = ListaRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        boolean eliminado = lista.getClientes().removeIf(c -> c.getId().equals(clienteId));

        if (!eliminado) {
            throw new RuntimeException("El cliente con ID " + clienteId + " no pertenece a esta lista");
        }

        ListaRepository.save(lista);
    }


}