package com.joa.springboot.FormaDePago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaDePagoService {

    @Autowired
    private FormaDePagoRepository formaDePagoRepository;

    public FormaDePagoResponseDTO createFormaDePago(FormaDePagoRequestDTO requestDTO) {
        FormaDePago formaDePago = new FormaDePago(requestDTO.getNombre());
        formaDePago = formaDePagoRepository.save(formaDePago);
        return new FormaDePagoResponseDTO(formaDePago.getId(), formaDePago.getNombre());
    }

    public List<FormaDePagoResponseDTO> getAllFormasDePago() {
        return formaDePagoRepository.findAll().stream()
                .map(formaDePago -> new FormaDePagoResponseDTO(formaDePago.getId(), formaDePago.getNombre()))
                .collect(Collectors.toList());
    }

    public FormaDePagoResponseDTO getFormaDePagoById(Long id) {
        FormaDePago formaDePago = formaDePagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada con ID: " + id));
        return new FormaDePagoResponseDTO(formaDePago.getId(), formaDePago.getNombre());
    }

    public FormaDePagoResponseDTO updateFormaDePago(Long id, FormaDePagoRequestDTO requestDTO) {
        FormaDePago formaDePago = formaDePagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada con ID: " + id));
        formaDePago.setNombre(requestDTO.getNombre());
        formaDePago = formaDePagoRepository.save(formaDePago);
        return new FormaDePagoResponseDTO(formaDePago.getId(), formaDePago.getNombre());
    }

    public void deleteFormaDePago(Long id) {
        formaDePagoRepository.deleteById(id);
    }
}
