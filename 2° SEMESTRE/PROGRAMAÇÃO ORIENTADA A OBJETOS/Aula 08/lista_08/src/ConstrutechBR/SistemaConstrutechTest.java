package ConstrutechBR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class SistemaConstrutechTest {

    @Test
    @Order(1)
    public void caso1_criarProfissionalValido() {
        Profissional p = new Profissional("P01", "111.111.111-11", "Ana Engenheira");
        assertEquals("P01", p.getId());
        assertEquals("111.111.111-11", p.getCpf());
        assertEquals("Ana Engenheira", p.getNome());
    }
    
}
