package com.mycompany.tpdsw.dto;

import com.mycompany.tpdsw.model.Coordenada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {
    private Integer id;
    private String nombre;
    private String cuit;
    private String direccion;
    private String email;
    private Coordenada coordenada;

    /**
     * Atributos para el manejo de los errores.
     * - Un string es mas facil manejar y permite null.
     * - El parseo de un null a Double no se puede.
     * - Idem con Integer
     * 
     * Al asegurarse que no contengan NULL, se parsea a Double/Integer
     * Luego setear el atributo
     */
    private String longitud;
    private String latitud;
    private String IdText;

}
