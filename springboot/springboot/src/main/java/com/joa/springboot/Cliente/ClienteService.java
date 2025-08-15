package com.joa.springboot.Cliente;

import com.joa.springboot.Estado.Estado;
import com.joa.springboot.Estado.EstadoRepository;
import com.joa.springboot.HistorialAsistencia.HistorialAsistencia;
import com.joa.springboot.HistorialAsistencia.HistorialAsistenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private HistorialAsistenciaRepository historialAsistenciaRepository;

    // Crear cliente – Service
    public ClienteResponseDTO crearCliente(ClienteDTO dto) {
        Estado estado = estadoRepository.findById(dto.getEstadoId())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDocumento(dto.getDocumento());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setEstado(estado);

        cliente = clienteRepository.save(cliente);

        return mapToResponseDTO(cliente);
    }

    // Modificar cliente – Service
    public ClienteResponseDTO actualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Estado estado = estadoRepository.findById(dto.getEstadoId())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDocumento(dto.getDocumento());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setEstado(estado);

        cliente = clienteRepository.save(cliente);

        return mapToResponseDTO(cliente);
    }

    //Mapeo auxiliar a DTO
    private ClienteResponseDTO mapToResponseDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setDocumento(cliente.getDocumento());
        dto.setTelefono(cliente.getTelefono());
        dto.setEmail(cliente.getEmail());
        dto.setEstadoNombre(cliente.getEstado() != null ? cliente.getEstado().getNombre() : null);
        return dto;
    }



    // Registrar asistencia (guardar en historial y actualizar estado del cliente)
    public void actualizarHistorialAsistencia(Long clienteId, Long estadoId) {
    Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    Estado estado = estadoRepository.findById(estadoId)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

    HistorialAsistencia historial = new HistorialAsistencia();
    historial.setCliente(cliente);
    historial.setEstado(estado);
    historial.setFechaHora(LocalDateTime.now());

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

    public long contarAsistenciasPresente(Long clienteId) {
        return historialAsistenciaRepository.countByClienteIdAndEstadoNombre(clienteId, "Presente");
    }
}
