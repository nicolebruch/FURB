package test;

import model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.EstoqueService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários do Sistema de Controle de Estoque.
 * Inclui todos os cenários CT01 a CT11 do Plano de Testes.
 */
public class SistemaEstoqueTest {

    // ---------------------------------------------------------
    // CT01 – Cadastrar Produto
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT01 - Cadastrar Produto")
    public void testCT01_CadastrarProduto() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("1", "Mouse", "Hardware", 50.0, 0);

        service.cadastrarProduto(p);

        assertEquals(1, service.getProdutos().size());
        assertSame(p, service.getProdutos().get(0));
    }


    // ---------------------------------------------------------
    // CT02 – Registrar Entrada
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT02 - Registrar Entrada (10 + 5 = 15)")
    public void testCT02_RegistrarEntrada() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("2", "Teclado", "Hardware", 100.0, 10);
        service.cadastrarProduto(p);

        Entrada e = new Entrada(p, LocalDate.now(), 5, 100.0);
        service.registrarEntrada(e);

        assertEquals(15, p.getQuantidadeEstoque());
    }


    // ---------------------------------------------------------
    // CT03 – Registrar Saída válida
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT03 - Registrar Saída válida (20 - 5 = 15)")
    public void testCT03_RegistrarSaidaValida() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("3", "HD", "Hardware", 200.0, 0);
        service.cadastrarProduto(p);

        service.registrarEntrada(new Entrada(p, LocalDate.of(2025, 1, 10), 20, 200.0));

        Saida s = new Saida(p, LocalDate.of(2025, 1, 15), 5, 200.0, "Venda ao cliente");
        boolean ok = service.registrarSaida(s);

        assertTrue(ok);
        assertEquals(15, p.getQuantidadeEstoque());
    }


    // ---------------------------------------------------------
    // CT04 – Registrar Saída sem saldo
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT04 - Registrar Saída sem saldo (estoque = 3, saída = 10 (rejeita)")
    public void testCT04_RegistrarSaidaSemSaldo() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("4", "Gabinete", "Hardware", 300.0, 0);
        service.cadastrarProduto(p);

        service.registrarEntrada(new Entrada(p, LocalDate.of(2025, 1, 10), 3, 300.0));

        Saida s = new Saida(p, LocalDate.of(2025, 1, 15), 10, 300.0, "Venda ao cliente");
        boolean ok = service.registrarSaida(s);

        assertFalse(ok);
        assertEquals(3, p.getQuantidadeEstoque());
    }


    // ---------------------------------------------------------
    // CT05 – Saída retroativa inválida
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT05 - Saída retroativa inválida (2024 < 2025)")
    public void testCT05_SaidaRetroativaInvalida() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("5", "Fonte", "Hardware", 250.0, 0);
        service.cadastrarProduto(p);

        service.registrarEntrada(new Entrada(p, LocalDate.of(2025, 1, 10), 5, 250.0));

        Saida s = new Saida(p, LocalDate.of(2024, 12, 1), 10, 250.0, "Venda ao cliente");
        boolean ok = service.registrarSaida(s);

        assertFalse(ok);
        assertEquals(5, p.getQuantidadeEstoque());
    }


    // ---------------------------------------------------------
    // CT06 – Calcular saldo atual
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT06 - Calcular saldo atual = 7")
    public void testCT06_CalcularSaldoAtual() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("6", "Placa-mãe", "Hardware", 500.0, 0);
        service.cadastrarProduto(p);

        service.registrarEntrada(new Entrada(p, LocalDate.now(), 10, 500.0));
        service.registrarSaida(new Saida(p, LocalDate.now(), 3, 500.0, "Venda"));

        assertEquals(7, service.calcularSaldoAtual(p));
    }


    // ---------------------------------------------------------
    // CT07 – Valor total em estoque
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT07 - Valor total estoque = 190")
    public void testCT07_ValorTotalEstoque() {
        EstoqueService service = new EstoqueService();

        Produto p1 = new Produto("7", "Teclado", "Hardware", 50.0, 2);  // 100
        Produto p2 = new Produto("8", "Mouse", "Hardware", 30.0, 3);    // 90

        service.cadastrarProduto(p1);
        service.cadastrarProduto(p2);

        assertEquals(190.0, service.calcularValorTotalEstoque(), 0.0001);
    }


    // ---------------------------------------------------------
    // CT08 – Saldo por período
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT08 - Saldo no período = 100")
    public void testCT08_SaldoPorPeriodo() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("9", "SSD", "Hardware", 200.0, 0);
        service.cadastrarProduto(p);

        // Fora do período
        service.registrarEntrada(new Entrada(p, LocalDate.of(2024, 12, 31), 1, 200.0));

        // Dentro do período (200 -100 = 100)
        service.registrarEntrada(new Entrada(p, LocalDate.of(2025, 1, 5), 2, 100.0));   // +200
        service.registrarSaida(new Saida(p, LocalDate.of(2025, 1, 10), 1, 100.0, "Venda")); // -100

        double saldo = service.calcularSaldoTotalPeriodo(
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 31)
        );

        assertEquals(100.0, saldo, 0.0001);
    }


    // ---------------------------------------------------------
    // CT09 – Listar Entradas
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT09 - Listar apenas entradas")
    public void testCT09_ListarEntradas() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("10", "Memória", "Hardware", 150.0, 0);
        service.cadastrarProduto(p);

        service.registrarEntrada(new Entrada(p, LocalDate.now(), 10, 150.0));
        service.registrarSaida(new Saida(p, LocalDate.now(), 2, 150.0, "Venda"));

        List<Entrada> entradas = service.listarEntradas();

        assertEquals(1, entradas.size());
        assertTrue(entradas.get(0) instanceof Entrada);
    }


    // ---------------------------------------------------------
    // CT10 – Listar Saídas
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT10 - Listar apenas saídas")
    public void testCT10_ListarSaidas() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("11", "Memória", "Hardware", 150.0, 0);
        service.cadastrarProduto(p);

        service.registrarEntrada(new Entrada(p, LocalDate.now(), 10, 150.0));
        service.registrarSaida(new Saida(p, LocalDate.now(), 3, 150.0, "Venda"));

        List<Saida> saidas = service.listarSaidas();

        assertEquals(1, saidas.size());
        assertTrue(saidas.get(0) instanceof Saida);
    }


    // ---------------------------------------------------------
    // CT11 – Movimentos ordenados
    // ---------------------------------------------------------
    @Test
    @DisplayName("CT11 - Movimentos ordenados por data")
    public void testCT11_ListarMovimentosOrdenados() {
        EstoqueService service = new EstoqueService();
        Produto p = new Produto("12", "Placa de vídeo", "Hardware", 1500.0, 0);
        service.cadastrarProduto(p);

        Entrada e2 = new Entrada(p, LocalDate.of(2025, 1, 5), 1, 1500.0);
        Entrada e1 = new Entrada(p, LocalDate.of(2025, 3, 10), 1, 1500.0);

        service.registrarEntrada(e1); // 10/03/2025
        service.registrarEntrada(e2); // 05/01/2025

        List<Movimento> ordenados = service.listarMovimentosOrdenados();

        assertEquals(LocalDate.of(2025, 1, 5), ordenados.get(0).getData());
        assertEquals(LocalDate.of(2025, 3, 10), ordenados.get(1).getData());
    }
}
