package com.mycompany.tpdsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.tpdsw.dto.VendedorDto;
import com.mycompany.tpdsw.service.VendedorService;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // JSON
    @GetMapping("/findAll")
    public ResponseEntity<List<VendedorDto>> getVendedores() {
        List<VendedorDto> vendedores = vendedorService.findAllByActivoTrue();
        if (vendedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vendedores);
    }

    // Thymeleaf
    @GetMapping("/list")
    public String getVendedoresPage(Model model) {
        List<VendedorDto> vendedores = vendedorService.findAllByActivoTrue();
        model.addAttribute("vendedores", vendedores);
        return "lista-vendedores";
    }

}
