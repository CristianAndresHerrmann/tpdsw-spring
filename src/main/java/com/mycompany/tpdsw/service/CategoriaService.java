package com.mycompany.tpdsw.service;

import com.mycompany.tpdsw.dto.CategoriaDto;

public interface CategoriaService {

    CategoriaDto findByNombre(String nombre);
}
