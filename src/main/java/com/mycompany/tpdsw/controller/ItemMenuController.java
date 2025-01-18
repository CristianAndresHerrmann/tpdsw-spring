package com.mycompany.tpdsw.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.tpdsw.dto.ItemMenuDto;
import com.mycompany.tpdsw.service.ItemMenuService;

@Controller
@RequestMapping("/item-menu")
public class ItemMenuController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ItemMenuController.class);

    @Autowired
    private ItemMenuService itemMenuService;

    @GetMapping("/{id}")
    public String mostrarItemMenuPorVendedorId(@PathVariable Integer id, Model model) {
        List<ItemMenuDto> items = itemMenuService.findActiveByIdVendedor(id);
        logger.info("Items Menu recuperados {}", items);
        if (Optional.of(items).isPresent()) {
            model.addAttribute("itemMenu", items);
            return "items-vendedor";
        } else {
            return "items-vendedor-no-encontrado";
        }
    }
}
