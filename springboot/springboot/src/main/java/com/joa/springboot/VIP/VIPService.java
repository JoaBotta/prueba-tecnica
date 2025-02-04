package com.joa.springboot.VIP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VIPService {

    @Autowired
    private VIPRepository VIPRepository;

    // Crear una nueva VIP
    public VIPResponseDTO crearVIP(VIPRequestDTO requestDTO) {
        VIP VIP = new VIP(requestDTO.getTelefono(),requestDTO.getCorreo(), requestDTO.getPrecio());
        VIP = VIPRepository.save(VIP);
        VIP.setQrCode(UUID.randomUUID().toString()); // Generar QR único

        return new VIPResponseDTO(VIP.getId(), VIP.getTelefono(), VIP.getCorreo(), VIP.getPrecio(), VIP.getQrCode());
    }

    // Listar todas las VIPs
    public List<VIPResponseDTO> listarVIP() {
        return VIPRepository.findAll().stream()
                .map(VIP -> new VIPResponseDTO(VIP.getId(), VIP.getTelefono(), VIP.getCorreo(), VIP.getPrecio(), VIP.getQrCode()))
                .collect(Collectors.toList());
    }

    // Obtener una VIP por ID
    public VIPResponseDTO obtenerVIPPorId(Long id) {
        VIP VIP = VIPRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VIP no encontrada con ID: " + id));

        return new VIPResponseDTO(VIP.getId(), VIP.getTelefono(), VIP.getCorreo(), VIP.getPrecio(), VIP.getQrCode());
    }

    // Actualizar una VIP
    public VIPResponseDTO actualizarVIP(Long id, VIPRequestDTO requestDTO) {
        VIP VIP = VIPRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VIP no encontrada con ID: " + id));

        VIP.setTelefono(requestDTO.getTelefono());
        VIP.setCorreo(requestDTO.getCorreo());
        VIP.setPrecio(requestDTO.getPrecio());

        VIP = VIPRepository.save(VIP);

        return new VIPResponseDTO(VIP.getId(), VIP.getTelefono(), VIP.getCorreo(), VIP.getPrecio(), VIP.getQrCode());
    }

    // Eliminar una VIP
    public void eliminarVIP(Long id) {
        VIPRepository.deleteById(id);
    }
}
