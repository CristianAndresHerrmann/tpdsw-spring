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
import com.mycompany.tpdsw.dto.CoordenadaDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.mapper.ClienteMapper;
import com.mycompany.tpdsw.model.Cliente;
import com.mycompany.tpdsw.model.Coordenada;
import com.mycompany.tpdsw.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDto> clientesDto = clientes.stream().map(clienteMapper::mapToDto).toList();
        return ResponseEntity.ok(clientesDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ClienteNoEncontradoException {
        Cliente cliente = clienteService.findById(id);
        if (cliente != null) {
            ClienteDto clienteDto = clienteMapper.mapToDto(cliente);
            return ResponseEntity.ok(clienteDto);
        } else {
            throw new ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado.");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.mapToModel(clienteDto);
        Cliente clienteGuardado = clienteService.save(cliente);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteDto clienteDto)
            throws ClienteNoEncontradoException {
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
