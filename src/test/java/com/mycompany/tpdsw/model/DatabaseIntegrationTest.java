package com.mycompany.tpdsw.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycompany.tpdsw.repository.VendedorRepository;

@SpringBootTest
public class DatabaseIntegrationTest {

    @Test
    public void tableLoadsTest() {

    }

    @Autowired
    private VendedorRepository vendedorRepository;

    @Test
    public void testSaveVendedor() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre("Juan");
        vendedor.setDireccion("Calle Falsa 123");
        // Configura la coordenada, etc.

        vendedorRepository.save(vendedor);

        // Verifica si el vendedor se ha guardado correctamente
        assert vendedorRepository.findById(vendedor.getId()).isPresent();
    }
}
