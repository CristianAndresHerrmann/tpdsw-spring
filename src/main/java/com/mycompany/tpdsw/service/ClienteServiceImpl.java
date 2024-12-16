package com.mycompany.tpdsw.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Integer id) throws ClienteNoEncontradoException {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ClienteNoEncontradoException("Cliente "+ id + " no encontrado"));
            return cliente;

    }
    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    @Override
    public Cliente update(Integer id, Cliente cliente) throws ClienteNoEncontradoException {
            return clienteRepository.findById(id)
                    .map(clienteEncontrado -> {
                        clienteEncontrado.setNombre(cliente.getNombre());
                        clienteEncontrado.setEmail(cliente.getEmail());
                        clienteEncontrado.setDireccion(cliente.getDireccion());
                        return clienteRepository.save(clienteEncontrado);
                    })
                    .orElseThrow(() -> new ClienteNoEncontradoException("Cliente "+ id + " no encontrado"));
        
    }

    @Override
    public void delete(Cliente cliente) throws ClienteNoEncontradoException {
        clienteRepository.delete(cliente);
    }
    
}
