package com.mycompany.tpdsw.mapper;

import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.model.Pedido;

import lombok.Builder;


@Builder
public class PedidoMapper implements Mapper<Pedido, PedidoDto> {

    @Override
    public PedidoDto mapToDto(Pedido entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapToDto'");
    }

    @Override
    public Pedido mapToEntity(PedidoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapToEntity'");
    }
    
}
