package com.joa.springboot.Cliente;

import com.joa.springboot.Estado.Estado;
import com.joa.springboot.Estado.EstadoRepository;
import com.joa.springboot.HistorialAsistencia.HistorialAsistencia;
import com.joa.springboot.HistorialAsistencia.HistorialAsistenciaRepository;
import com.joa.springboot.Lista.Lista;
import com.joa.springboot.Lista.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private HistorialAsistenciaRepository historialAsistenciaRepository;

    // Crear un cliente en una lista, asignándole un Estado inicial
    public ClienteResponseDTO agregarCliente(Long listaId, ClienteDTO dto) {
        Optional<Lista> optionalLista = listaRepository.findById(listaId);
        if (optionalLista.isEmpty()) {
            throw new RuntimeException("Lista no encontrada");
        }

        Estado estado = estadoRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDocumento(dto.getDocumento());
        cliente.setTelefono(dto.getTelefono());
        cliente.setLista(optionalLista.get());
        cliente.setEstado(estado); // Asignamos el estado actual

        clienteRepository.save(cliente);

        return convertirAResponseDTO(cliente);
    }

    // Obtener todos los clientes de una lista
    public List<ClienteResponseDTO> obtenerClientesPorLista(Long listaId) {
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        List<Cliente> clientes = clienteRepository.findByLista(lista);

        return clientes.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    // Registrar asistencia (guardar en historial y actualizar estado del cliente)
    public void actualizarHistorialAsistencia(Long clienteId, Long estadoId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
    
        HistorialAsistencia historial = new HistorialAsistencia();
        historial.setEstado(estado);
        historialAsistenciaRepository.save(historial);
    
        cliente.setEstado(estado);
        clienteRepository.save(cliente);
    }
    // Cambiar directamente el estado actual de un cliente
    public void cambiarEstadoCliente(Long clienteId, Long nuevoEstadoId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Estado nuevoEstado = estadoRepository.findById(nuevoEstadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        cliente.setEstado(nuevoEstado);

        clienteRepository.save(cliente);
    }

    // Convertir Cliente -> ClienteResponseDTO
    private ClienteResponseDTO convertirAResponseDTO(Cliente cliente) {
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setDocumento(cliente.getDocumento());
        response.setTelefono(cliente.getTelefono());
        response.setEstadoNombre(cliente.getEstado() != null ? cliente.getEstado().getNombre() : null);
        return response;
    }
}
