package com.joa.springboot.VentaEntradaOnline;

import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.EntradaOnline.EntradaOnlineRepository;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.FormaDePago.FormaDePagoRepository;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaRepository;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntradaResponseDTO;
import com.joa.springboot.EntradaGenerada.EntradaGenerada;
import com.joa.springboot.EntradaGenerada.EntradaGeneradaResponseDTO;
import com.joa.springboot.EntradaGenerada.EntradaGeneradaRepository;
import com.joa.springboot.Cliente.ClienteResponseDTO;
import com.joa.springboot.Cliente.ClienteService;
import com.joa.springboot.Usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Boliche.BolicheRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.joa.springboot.Cliente.Cliente;
import com.joa.springboot.Cliente.ClienteRepository;

@Service
public class VentaEntradaOnlineService {

    @Autowired
    private VentaEntradaOnlineRepository ventaEntradaOnlineRepository;

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    @Autowired
    private DetalleVentaEntradaRepository detalleVentaEntradaRepository;

    @Autowired
    private EntradaOnlineRepository entradaOnlineRepository;

    @Autowired
    private EntradaGeneradaRepository entradaGeneradaRepository;

    @Autowired
    private BolicheRepository bolicheRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    

    public VentaEntradaOnlineResponseDTO crearVentaEntradaOnline(VentaEntradaOnlineRequestDTO dto) {
        Boliche boliche = bolicheRepository.findById(dto.getBolicheId())
            .orElseThrow(() -> new RuntimeException("Boliche no encontrado"));

        FormaDePago formaDePago = formaDePagoRepository.findById(dto.getFormaDePagoId())
            .orElseThrow(() -> new IllegalArgumentException("Forma de Pago no encontrada"));
        
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        VentaEntradaOnline venta = new VentaEntradaOnline();
        venta.setBoliche(boliche);
        venta.setFormaDePago(formaDePago);
        venta.setFechaHora(dto.getFechaHora());
         venta.setCliente(cliente);
        venta.setTotalPrecio(dto.getTotalPrecio());

        venta = ventaEntradaOnlineRepository.save(venta);

        List<DetalleVentaEntrada> detalles = new ArrayList<>();
        for (var detalleDto : dto.getDetalleVentaEntrada()) {
            EntradaOnline entradaOnline = entradaOnlineRepository.findById(detalleDto.getEntradaId())
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

            DetalleVentaEntrada detalle = new DetalleVentaEntrada();
            detalle.setVentaEntradaOnline(venta);
            detalle.setEntradaOnline(entradaOnline);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setSubtotal(detalleDto.getCantidad() * entradaOnline.getPrecio().doubleValue());

            List<EntradaGenerada> entradasGeneradas = new ArrayList<>();
            for (int i = 0; i < detalleDto.getCantidad(); i++) {
                EntradaGenerada entradaGenerada = new EntradaGenerada();
                entradaGenerada.setDetalleVentaEntrada(detalle);

                String qrContent = String.format(
                    "VENTA: %d | ENTRADA: %d | CANTIDAD: %d",
                    venta.getId(),
                    detalle.getEntradaOnline().getId(),
                    detalle.getCantidad()
                );

                entradaGenerada.setQrCode(qrContent);
                entradasGeneradas.add(entradaGenerada);
            }

            detalle.setEntradasGeneradas(entradasGeneradas);
            detalle = detalleVentaEntradaRepository.save(detalle);
            entradaGeneradaRepository.saveAll(entradasGeneradas);
            detalles.add(detalle);
        }

        venta.setDetalleVentaEntrada(detalles);
        venta.setTotalPrecio(detalles.stream().mapToDouble(DetalleVentaEntrada::getSubtotal).sum());
        ventaEntradaOnlineRepository.save(venta);

        return mapToDTO(venta);
    }

    public List<VentaEntradaOnlineResponseDTO> obtenerTodas() {
        return ventaEntradaOnlineRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public VentaEntradaOnlineResponseDTO obtenerPorId(Long id) {
        VentaEntradaOnline venta = ventaEntradaOnlineRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        return mapToDTO(venta);
    }

    private VentaEntradaOnlineResponseDTO mapToDTO(VentaEntradaOnline venta) {
    VentaEntradaOnlineResponseDTO dto = new VentaEntradaOnlineResponseDTO();
    dto.setId(venta.getId());
    dto.setFormaDePagoId(venta.getFormaDePago().getId());
    dto.setFechaHora(venta.getFechaHora());
    dto.setTotalPrecio(venta.getTotalPrecio());
    dto.setClienteId(venta.getCliente().getId());

    if (venta.getDetalleVentaEntrada() != null) {
        List<DetalleVentaEntradaResponseDTO> detallesDTO = venta.getDetalleVentaEntrada()
            .stream()
            .map(detalle -> new DetalleVentaEntradaResponseDTO(
                detalle.getId(),
                detalle.getEntradaOnline().getId(),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                detalle.getEntradasGeneradas() != null
                    ? detalle.getEntradasGeneradas().stream()
                        .map(entrada -> new EntradaGeneradaResponseDTO(
                            entrada.getId(),
                            entrada.getQrCode(),
                            entrada.getUsada()))
                        .collect(Collectors.toList())
                    : new ArrayList<>()
            ))
            .collect(Collectors.toList());

        dto.setDetalleVentaEntrada(detallesDTO);
    }

        return dto;
    }

    //eliminar venta
    public void eliminarVenta(Long id) {
        VentaEntradaOnline venta = ventaEntradaOnlineRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
        ventaEntradaOnlineRepository.delete(venta);
    }
}
