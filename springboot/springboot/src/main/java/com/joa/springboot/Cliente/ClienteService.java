package com.joa.springboot.Cliente;

import com.joa.springboot.Cliente.Cliente;
import com.joa.springboot.Cliente.ClienteDTO;
import com.joa.springboot.Cliente.ClienteResponseDTO;
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

    public ClienteResponseDTO agregarCliente(Long listaId, ClienteDTO dto) {
        Optional<Lista> optionalLista = listaRepository.findById(listaId);
        if (optionalLista.isEmpty()) {
            throw new RuntimeException("Lista no encontrada");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDocumento(dto.getDocumento());
        cliente.setTelefono(dto.getTelefono());
        cliente.setLista(optionalLista.get());

        clienteRepository.save(cliente);

        return convertirAResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> obtenerClientesPorLista(Long listaId) {
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        List<Cliente> clientes = clienteRepository.findByLista(lista);

        return clientes.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public void actualizarHistorialAsistencia(Long clienteId, Boolean asistencia) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        HistorialAsistencia historial = new HistorialAsistencia();
        historial.setPresente(asistencia);
        historial.setCliente(cliente);

        cliente.getHistorialAsistencia().add(historial);
        clienteRepository.save(cliente);
    }

    private ClienteResponseDTO convertirAResponseDTO(Cliente cliente) {
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setDocumento(cliente.getDocumento());
        response.setTelefono(cliente.getTelefono());
        // Devuelve el historial de asistencia en el DTO
        response.setAsistencia(cliente.getHistorialAsistencia().stream()
                .anyMatch(HistorialAsistencia::isPresente));
        return response;
    }
}
