package com.mycompany.tpdsw.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.tpdsw.dto.PedidoItemPedidoDto;
import com.mycompany.tpdsw.model.relacion.PedidoItemPedido;

import lombok.Builder;

@Builder
@Component
public class PedidoItemPedidoMapper implements Mapper<PedidoItemPedido, PedidoItemPedidoDto> {

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @Override
    public PedidoItemPedidoDto mapToDto(PedidoItemPedido entity) {
        return PedidoItemPedidoDto.builder()
                .id(entity.getId())
                .pedidoDto(pedidoMapper.mapToDto(entity.getPedido()))
                .itemPedidoDto(itemPedidoMapper.mapToDto(entity.getItemPedido()))
                .build();
    }

    @Override
    public PedidoItemPedido mapToEntity(PedidoItemPedidoDto dto) {
        return PedidoItemPedido.builder()
                .id(dto.getId())
                .pedido(pedidoMapper.mapToEntity(dto.getPedidoDto()))
                .itemPedido(itemPedidoMapper.mapToEntity(dto.getItemPedidoDto()))
                .build();
    }

}
