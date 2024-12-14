package com.mycompany.tpdsw.service;

import java.util.List;
import java.util.Optional;

import com.mycompany.tpdsw.model.Cliente;

public interface ClienteService {
    
    public List<Cliente> findAll();

    public Optional<Cliente> findById(Integer id);

    public void save(Cliente cliente);

    public void delete(Cliente cliente);
}
