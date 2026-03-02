package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.Entrada;
import model.Movimento;
import model.Produto;
import model.Saida;

/**
 * Serviço de negócio responsável por gerenciar produtos e movimentos
 * de estoque, incluindo entradas, saídas e consultas de saldo.
 */
public class EstoqueService {

    private List<Produto> produtos = new ArrayList<>();
    private List<Movimento> movimentos = new ArrayList<>();

    // -----------------------------------------------------
    // PRODUTOS
    // -----------------------------------------------------

    /**
     * Cadastra um novo produto.
     *
     * @param p produto a ser cadastrado
     */
    public void cadastrarProduto(Produto p) {
        produtos.add(p);
    }

    // -----------------------------------------------------
    // ENTRADAS
    // -----------------------------------------------------

    /**
     * Registra uma entrada, aplicando-a ao estoque.
     *
     * @param e entrada registrada
     */
    public void registrarEntrada(Entrada e) {
        e.aplicar();
        movimentos.add(e);
    }

    // -----------------------------------------------------
    // SAÍDAS
    // -----------------------------------------------------

    /**
     * Registra uma saída após verificar se o estoque histórico
     * permite a operação.
     *
     * @param s saída registrada
     * @return true se a saída foi aceita; false caso contrário
     */
    public boolean registrarSaida(Saida s) {
        if (!estoqueHistoricoPermiteSaida(s)) {
            return false;
        }
        s.aplicar();
        movimentos.add(s);
        return true;
    }

    /**
     * Verifica se o saldo histórico de um produto permite
     * registrar a nova saída.
     *
     * @param novaSaida saída a validar
     * @return true se o saldo não fica negativo em nenhum momento
     */
    private boolean estoqueHistoricoPermiteSaida(Saida novaSaida) {

        List<Movimento> historico = movimentos.stream()
                .filter(m -> m.getProduto().getCodigo()
                        .equalsIgnoreCase(novaSaida.getProduto().getCodigo()))
                .collect(Collectors.toCollection(ArrayList::new));

        historico.add(novaSaida);
        historico.sort(Comparator.comparing(Movimento::getData));

        int saldo = 0;

        for (Movimento m : historico) {
            if (m instanceof Entrada) saldo += m.getQuantidade();
            else saldo -= m.getQuantidade();

            if (saldo < 0) return false;
        }

        return true;
    }

    // -----------------------------------------------------
    // CONSULTAS DE LISTA
    // -----------------------------------------------------

    /**
     * Retorna a lista de entradas registradas.
     *
     * @return lista de entradas
     */
    public List<Entrada> listarEntradas() {
        return movimentos.stream()
                .filter(m -> m instanceof Entrada)
                .map(m -> (Entrada) m)
                .collect(Collectors.toList());
    }

    /**
     * Retorna a lista de saídas registradas.
     *
     * @return lista de saídas
     */
    public List<Saida> listarSaidas() {
        return movimentos.stream()
                .filter(m -> m instanceof Saida)
                .map(m -> (Saida) m)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todos os movimentos ordenados por data.
     *
     * @return lista de movimentos ordenados
     */
    public List<Movimento> listarMovimentosOrdenados() {
        return movimentos.stream()
                .sorted(Comparator.comparing(Movimento::getData))
                .collect(Collectors.toList());
    }

    // -----------------------------------------------------
    // CONSULTAS DE SALDO
    // -----------------------------------------------------

    /**
     * Retorna o saldo atual de um produto.
     *
     * @param prod produto desejado
     * @return saldo atual
     */
    public int calcularSaldoAtual(Produto prod) {
        return prod.getQuantidadeEstoque();
    }

    /**
     * Calcula o valor total do estoque atual.
     *
     * @return soma dos valores em estoque
     */
    public double calcularValorTotalEstoque() {
        return produtos.stream()
                .mapToDouble(Produto::getValorTotal)
                .sum();
    }

    /**
     * Calcula o saldo financeiro total em um período.
     *
     * @param inicio data inicial
     * @param fim data final
     * @return saldo financeiro (entradas - saídas)
     */
    public double calcularSaldoTotalPeriodo(LocalDate inicio, LocalDate fim) {
        return movimentos.stream()
                .filter(m -> !m.getData().isBefore(inicio) && !m.getData().isAfter(fim))
                .mapToDouble(m -> m instanceof Saida ? -m.getValorTotal() : m.getValorTotal())
                .sum();
    }

    // -----------------------------------------------------
    // GETTERS
    // -----------------------------------------------------

    /**
     * Retorna a lista de produtos cadastrados.
     *
     * @return lista de produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Retorna a lista de movimentos registrados.
     *
     * @return lista de movimentos
     */
    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    /**
     * Busca um produto pelo código.
     *
     * @param codigo código do produto
     * @return produto encontrado ou null
     */
    public Produto buscarProdutoPorCodigo(String codigo) {
        return produtos.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

}
