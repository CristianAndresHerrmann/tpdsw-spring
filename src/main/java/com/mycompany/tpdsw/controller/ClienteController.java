package com.mycompany.tpdsw.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ClienteDto> clientes = clienteService.findAll()
                    .stream().map(cliente -> ClienteDto.builder()
                        .id(cliente.getId())
                        .nombre(cliente.getNombre())
                        .cuit(cliente.getCuit())
                        .direccion(cliente.getDireccion())
                        .email(cliente.getEmail())
                        .coordenada(cliente.getCoordenada())
                    .build())
                    .toList();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ClienteNoEncontradoException {
        Optional<Cliente> cliente = Optional.of(clienteService.findById(id));
        if (cliente.isPresent()) {
            ClienteDto clienteDto = ClienteDto.builder()
                    .id(cliente.get().getId())
                    .nombre(cliente.get().getNombre())
                    .cuit(cliente.get().getCuit())
                    .direccion(cliente.get().getDireccion())
                    .email(cliente.get().getEmail())
                    .coordenada(cliente.get().getCoordenada())
                .build();
            return ResponseEntity.ok(clienteDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto) throws URISyntaxException {
            if(!clienteDto.getId().equals(null)){
                return ResponseEntity.badRequest().build();
            }
            clienteService.save(Cliente.builder()
                    .nombre(clienteDto.getNombre())
                    .cuit(clienteDto.getCuit())
                    .direccion(clienteDto.getDireccion())
                    .email(clienteDto.getEmail())
                    .coordenada(clienteDto.getCoordenada())
                .build());
            return ResponseEntity.created(new URI("/api/cliente/save")).build();
        }   

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteDto clienteDto) throws ClienteNoEncontradoException {
        Optional<Cliente> cliente = Optional.of(clienteService.findById(id));
        if (cliente.isPresent()) {
            clienteService.update(id, Cliente.builder()
                    .nombre(clienteDto.getNombre())
                    .cuit(clienteDto.getCuit())
                    .direccion(clienteDto.getDireccion())
                    .email(clienteDto.getEmail())
                    .coordenada(clienteDto.getCoordenada())
                .build());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ClienteNoEncontradoException {
        Optional<Cliente> cliente = Optional.of(clienteService.findById(id));
        if (cliente.isPresent()) {
            clienteService.delete(cliente.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
