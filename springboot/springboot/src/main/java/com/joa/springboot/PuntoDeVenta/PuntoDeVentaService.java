package com.joa.springboot.PuntoDeVenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Boliche.BolicheRepository;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.Usuario.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuntoDeVentaService {

    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    private BolicheRepository bolicheRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Crear un nuevo Punto de Venta
    public PuntoDeVentaResponseDTO createPuntoDeVenta(PuntoDeVentaRequestDTO requestDTO) {
        Boliche boliche = bolicheRepository.findById(requestDTO.getBolicheId())
                .orElseThrow(() -> new RuntimeException("Boliche no encontrado"));

        Usuario seguridad = usuarioRepository.findById(requestDTO.getSeguridadId())
                .orElseThrow(() -> new RuntimeException("Usuario de seguridad no encontrado"));

        PuntoDeVenta puntoDeVenta = new PuntoDeVenta(requestDTO.getNombre(), boliche, seguridad);
        puntoDeVenta = puntoDeVentaRepository.save(puntoDeVenta);

        return mapToDTO(puntoDeVenta);
    }

    // ✅ Obtener todos los puntos de venta
    public List<PuntoDeVentaResponseDTO> getAllPuntosDeVenta() {
        return puntoDeVentaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener un punto de venta por ID
    public PuntoDeVentaResponseDTO getPuntoDeVentaById(Long id) {
        return puntoDeVentaRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Punto de Venta no encontrado"));
    }

    // ✅ Obtener puntos de venta por boliche
    public List<PuntoDeVentaResponseDTO> getPuntosDeVentaByBoliche(Long bolicheId) {
        return puntoDeVentaRepository.findByBolicheId(bolicheId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Eliminar un punto de venta
    public void deletePuntoDeVenta(Long id) {
        puntoDeVentaRepository.deleteById(id);
    }

    // Método de conversión a DTO
    private PuntoDeVentaResponseDTO mapToDTO(PuntoDeVenta puntoDeVenta) {
        return new PuntoDeVentaResponseDTO(
                puntoDeVenta.getId(),
                puntoDeVenta.getNombre(),
                puntoDeVenta.getBoliche().getNombre(),
                puntoDeVenta.getSeguridad().getUsername()
        );
    }
}
