package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;

public interface PedidoService {
    
    List<PedidoDto> findAllActive();
    PedidoDto findByIdAndActive(Integer id) throws PedidoNoEncontradoException;
    void save(PedidoDto pedidoDto);
    void update(PedidoDto pedidoDto);
    void delete(PedidoDto pedidoDto);
    
}
