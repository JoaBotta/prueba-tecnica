package com.joa.springboot.Mensaje;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
    private int errorType;
    private String mensaje;

}
