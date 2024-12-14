
package com.mycompany.tpdsw.model;

import java.math.BigDecimal;

import com.mycompany.tpdsw.dto.PlatoDto;
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
@Table(name = "plato")
public class Plato extends ItemMenu {

    @Column(nullable = false)
    @Min(0)
    private Double calorias;

    @Column(nullable = false)
    private Boolean aptoCeliaco;

    @Column(nullable = false)
    private Boolean aptoVegetariano;

    @Column(nullable = false)
    private Boolean aptoVegano;

    @Column(nullable = false)

    private Double peso;

    // Constructor para modificar Plato
    public Plato(PlatoDto platoDto, Vendedor vendedor) {
        super(platoDto.getId(),
                platoDto.getNombre(),
                platoDto.getDescripcion(),
                platoDto.getPrecio(),
                platoDto.getCategoria(),
                vendedor);
        this.calorias = platoDto.getCalorias();
        this.aptoCeliaco = platoDto.getAptoCeliaco();
        this.aptoVegetariano = platoDto.getAptoVegetariano();
        this.aptoVegano = platoDto.getAptoVegano();
        this.peso = platoDto.getPeso();
    }

    public Plato(String nombre, Double calorias, Boolean aptoCeliaco, Boolean aptoVegetariano, Boolean aptoVegano,
            Double peso, Vendedor vendedor,
            Integer id, BigDecimal precio, String descripcion, Categoria categoria) {
        super(id, nombre, descripcion, precio, categoria, vendedor);
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegetariano = aptoVegetariano;
        this.aptoVegano = aptoVegano;
        this.peso = peso;
    }

    /**
     * Metodo que setea si el Plato es apto vegetariano
     * - Plato que no es apto vegetariano, tampoco es apto vegano
     * 
     * @param aptoVegetariano
     */
    public void setAptoVegetariano(Boolean aptoVegetariano) {
        this.aptoVegetariano = aptoVegetariano;
        if (!aptoVegetariano) {
            this.aptoVegano = false;
        }
    }

    /**
     * Metodo que setea si el Plato es apto vegano
     * - Plato que es apto vegano, tambien es apto vegetariano
     * 
     * @param aptoVegano
     */
    public void setAptoVegano(Boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
        if (aptoVegano) {
            this.aptoVegetariano = true;
        }
    }

    /**
     * Metodo de la regla de negocio
     * - Tiene en cuenta el peso del envase, 10%
     * Formula -> <peso del plato> * 1.1
     * 
     * @return Valor del peso real, teniendo en cuenta el envase
     */
    @Override
    public Double peso() {
        return this.peso * 1.1;
    }

    /**
     * Indica si el item es de tipo Comida
     * 
     * @return 'true' siempre ya que es plato
     */
    @Override
    public boolean esComida() {
        return true;
    }

    /**
     * Indica si el item es de tipo Bebida
     * 
     * @return 'false' siempre ya que es plato
     */
    @Override
    public boolean esBebida() {
        return false;
    }

    /**
     * Regla de negocio
     * - Igual a un getAptoVegano
     */
    @Override
    public boolean aptoVegano() {
        return aptoVegano;
    }

}
