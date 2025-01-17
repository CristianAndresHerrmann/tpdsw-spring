package com.mycompany.tpdsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.tpdsw.dto.VendedorDto;
import com.mycompany.tpdsw.service.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<VendedorDto>> getVendedores() {
        List<VendedorDto> vendedores = vendedorService.findAllByActivoTrue();
        if (vendedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vendedores);
    }

}
