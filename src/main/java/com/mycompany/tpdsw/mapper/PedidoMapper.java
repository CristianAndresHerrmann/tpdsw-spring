package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.model.Pedido;
import com.mycompany.tpdsw.service.ClienteService;

import lombok.Builder;

@Builder
@Component
public class PedidoMapper implements Mapper<Pedido, PedidoDto> {

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public PedidoDto mapToDto(Pedido entity) {
        return PedidoDto.builder()
                .clienteId(entity.getCliente().getId())
                .id(entity.getId())
                .estado(entity.getEstado())
                .formaPagoDto(pagoMapper.mapToDto(entity.getFormaPago()))
                .build();
    }

    @Override
    public Pedido mapToEntity(PedidoDto dto) { // Falta la lista de pedidos
        ClienteDto clienteDto;
        Cliente cliente;
        try {
            clienteDto = clienteService.findByIdAndActive(dto.getClienteId());
            cliente = clienteMapper.mapToEntity(clienteDto);
        } catch (ClienteNoEncontradoException e) {
            cliente = null;
        }

        return Pedido.builder()
                .id(dto.getId())
                .estado(dto.getEstado())
                .cliente(cliente)
                .formaPago(pagoMapper.mapToEntity(dto.getFormaPagoDto()))
                .build();

    }

}
