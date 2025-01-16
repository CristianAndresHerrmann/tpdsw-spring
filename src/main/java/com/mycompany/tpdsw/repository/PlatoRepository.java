package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.ItemMenu;
import com.mycompany.tpdsw.model.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

    public Plato findActivePlatoById(Integer id);

    public List<Plato> findActiveByIdVendedor(Integer id);

    public List<ItemMenu> findAllActive();
}
