package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.model.Cliente;

public interface ClienteService {
    
    List<Cliente> findAll();

    Cliente findById(Integer id) throws ClienteNoEncontradoException;

    void save(Cliente cliente);

    void delete(Cliente cliente) throws ClienteNoEncontradoException;

    Cliente update(Integer id, Cliente cliente) throws ClienteNoEncontradoException;
    
}
