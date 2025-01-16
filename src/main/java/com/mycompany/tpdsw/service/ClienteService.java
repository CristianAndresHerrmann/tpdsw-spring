package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;

public interface ClienteService {

    List<ClienteDto> findAll();

    ClienteDto findById(Integer id) throws ClienteNoEncontradoException;

    ClienteDto save(ClienteDto cliente);

    void delete(ClienteDto cliente) throws ClienteNoEncontradoException;

    ClienteDto update(ClienteDto cliente) throws ClienteNoEncontradoException;

}
