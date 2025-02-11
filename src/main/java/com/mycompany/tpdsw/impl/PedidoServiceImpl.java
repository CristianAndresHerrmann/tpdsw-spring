package com.mycompany.tpdsw.impl;

import com.mycompany.tpdsw.service.PedidoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.controller.VendedorController;
import com.mycompany.tpdsw.dto.PedidoDto;
import com.mycompany.tpdsw.exception.PedidoNoEncontradoException;
import com.mycompany.tpdsw.mapper.PedidoMapper;
import com.mycompany.tpdsw.model.Pedido;
import com.mycompany.tpdsw.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Override
    public List<PedidoDto> findAllActive() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedidoMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     * Registra un nuevo pedido en el sistema.
     * Convierte el DTO recibido en objeto dominio y se persiste
     * 
     * @param pedidoDto El objeto DTO que contiene los datos del pedido a registrar.
     */
    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        Pedido pedido = pedidoMapper.mapToEntity(pedidoDto);

        Pedido newPedido = pedidoRepository.save(pedido);
        logger.info("Pedido guardado: {}", newPedido);
        return pedidoMapper.mapToDto(newPedido);
    }

    @Override
    public PedidoDto update(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoMapper.mapToEntity(pedidoDto);
        logger.info("Pedido mappeado {}", pedido);
        Integer id = pedido.getId();

        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNoEncontradoException("Pedido " + id + " no encontrado");
        }

        pedido = pedidoRepository.save(pedido);

        return pedidoMapper.mapToDto(pedido);
    }

    @Override
    public void delete(PedidoDto pedidoDto) throws PedidoNoEncontradoException {
        Pedido pedido = pedidoMapper.mapToEntity(pedidoDto);
        Integer id = pedido.getId();
        if(!pedidoRepository.existsById(id)) {
            throw new PedidoNoEncontradoException("Pedido " + id + " no encontrado");
        }
        pedidoRepository.delete(pedido);

    }

    @Override
    public PedidoDto findById(Integer id) throws PedidoNoEncontradoException {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (!pedidoOpt.isPresent()) {
            throw new PedidoNoEncontradoException("No se ha encontrado pedido con id: " + id);
        }

        Pedido pedido = pedidoOpt.get();

        return pedidoMapper.mapToDto(pedido);

    }

}
