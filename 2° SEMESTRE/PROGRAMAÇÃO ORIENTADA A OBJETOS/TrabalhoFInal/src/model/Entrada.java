package model;

import java.time.LocalDate;

/**
 * Representa uma entrada de produtos no estoque.
 * Entradas aumentam a quantidade de um produto.
 */
public class Entrada extends Movimento {

    /**
     * Cria uma nova entrada de estoque.
     *
     * @param produto produto movimentado
     * @param data data da entrada
     * @param quantidade quantidade que está entrando
     * @param valorUnitario valor unitário do produto
     */
    public Entrada(Produto produto, LocalDate data, int quantidade, double valorUnitario) {
        super(produto, data, quantidade, valorUnitario);
    }

    // -----------------------------------------------------
    // REGRAS DE NEGÓCIO
    // -----------------------------------------------------

    /**
     * Aplica a entrada ao estoque, aumentando a quantidade.
     */
    @Override
    public void aplicar() {
        produto.adicionarQuantidade(quantidade);
    }
}
