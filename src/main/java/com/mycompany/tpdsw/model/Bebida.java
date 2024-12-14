/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpdsw.model;

import java.math.BigDecimal;

import com.mycompany.tpdsw.dto.BebidaDto;
import com.mycompany.tpdsw.model.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "Bebida")
public class Bebida extends ItemMenu {

    @Column(name = "graduacion_alcoholica")
    private Double graduacionAlcoholica;

    @Column(nullable = false)
    private Double tamano;

    @Column(nullable = false)
    private Double volumen;

    // Constructor para agregar Bebida
    public Bebida(BebidaDto bebidaDto, Vendedor vendedor) {
        super(bebidaDto.getId(),
                bebidaDto.getNombre(),
                bebidaDto.getDescripcion(),
                bebidaDto.getPrecio(),
                bebidaDto.getCategoria(),
                vendedor);
        this.graduacionAlcoholica = bebidaDto.getGraduacionAlcoholica();
        this.tamano = bebidaDto.getTamano();
        this.volumen = bebidaDto.getVolumen();
    }

    public Bebida(String nombre, Double graduacionAlcoholica, Double tamano, Double volumen,
            Integer id, BigDecimal precio, String descripcion, Categoria categoria, Vendedor vendedor) {
        super(id, nombre, descripcion, precio, categoria, vendedor);
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.tamano = tamano;
        this.volumen = volumen;
    }

    /**
     * Calcula el peso de la bebida considerando su volumen, tipo y 20% por envases.
     * - Bebida con alcohol: 0.99
     * - Bebida gaseosa: 1.04
     * 
     * Formula -> Volumen * <Factor Tipo> * 1.2
     */
    @Override
    public Double peso() {
        if (this.graduacionAlcoholica > 0) {
            return this.volumen * 0.99 * 1.2;
        } else {
            return this.volumen * 1.04 * 1.2;
        }
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean aptoVegano() {
        return false;
    }

}
