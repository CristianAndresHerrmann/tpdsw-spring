package com.mycompany.tpdsw.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.patronObserver.Observer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "cliente")
public class Cliente implements Observer<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String cuit;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Coordenada coordenada;

    @Column(name = "fecha_registro", nullable = false)
    private final LocalDate fechaRegistro = LocalDate.now();

    @Builder.Default
    private Boolean activo = true;

    @Column(name = "fecha_eliminacion")
    @Builder.Default
    private LocalDate fechaEliminacion = null;

    public Cliente(ClienteDto clienteDto) {
        this.id = clienteDto.getId();
        this.nombre = clienteDto.getNombre();
        this.cuit = clienteDto.getCuit();
        this.direccion = clienteDto.getDireccion();
        this.coordenada = clienteDto.getCoordenada();
        this.email = clienteDto.getEmail();
        this.activo = true;
    }

    /**
     * Metodo que actua como observador del Cliente
     * Ejecutado cuando se cambia el estado del pedido del cliente
     * - Si el estado cambia a ENVIADO, se genera el PAGO correspondiente
     * 
     * @param pedido El pedido cuyo estado ha cambiado
     */

    @Override
    public void updateEstado(Pedido pedido) {

        System.out.println("Se le notifica a " + nombre + ": El estado de su pedido (ID: " + pedido.getId()
                + ") ha cambiado a " + pedido.getEstado());

        if (pedido.getEstado().equals(Estado.ENVIADO)) {
            generarPago(pedido);
        }
    }

    /**
     * Metodo que genera y configura el pago para el pedido del cliente
     * - Dependiendo del metodo de pago, crea y lo establece al pedido
     * - Soporta pagos MercadoPago y Transferencia
     * 
     * @param pedido El pedido por el cual se debera generar el pago
     * @throws IllegalArgumentException Si el monto es negativo o si el tipo de pago
     *                                  no es soportado
     */

    private void generarPago(Pedido pedido) {

        BigDecimal monto = pedido.total();

        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("Monto negativo");
        }

        if (pedido.getFormaPago() instanceof MercadoPago) {
            MercadoPago mercadoPago = new MercadoPago("miAlias");
            mercadoPago.setMonto(monto);
            pedido.setFormaPago(mercadoPago);

        } else if (pedido.getFormaPago() instanceof Transferencia) {
            Transferencia transferencia = new Transferencia("cbu123", "20-123456-3");
            transferencia.setMonto(monto);
            pedido.setFormaPago(transferencia);

        } else {
            throw new IllegalArgumentException("Tipo de pago no seteado o no soportado");
        }
    }

}
