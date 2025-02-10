package com.mycompany.tpdsw.impl;

import com.mycompany.tpdsw.service.PedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;
import com.mycompany.tpdsw.mapper.PedidoMapper;
import com.mycompany.tpdsw.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService  {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;


    @Override
    public List<PedidoDto> findAllActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllActive'");
    }

    @Override
    public PedidoDto findByIdAndActive(Integer id) throws PedidoNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdAndActive'");
    }

    @Override
    public void save(PedidoDto pedidoDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(PedidoDto pedidoDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(PedidoDto pedidoDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    
}
