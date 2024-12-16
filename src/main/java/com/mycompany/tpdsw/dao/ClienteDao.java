package com.mycompany.tpdsw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.repository.ClienteRepository;


public class ClienteDao extends GenericDAO<Cliente, Integer> {

    public ClienteDao() {
        super(Cliente.class);
    }
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente update(Integer id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(clienteEncontrado -> {
                    clienteEncontrado.setNombre(cliente.getNombre());
                    clienteEncontrado.setEmail(cliente.getEmail());
                    clienteEncontrado.setDireccion(cliente.getDireccion());
                    return clienteRepository.save(clienteEncontrado);
                })
                .orElse(null);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
    

}
