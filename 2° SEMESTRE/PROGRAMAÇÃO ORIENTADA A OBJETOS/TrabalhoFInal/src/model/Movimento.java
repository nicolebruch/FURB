package model;

import java.time.LocalDate;

/**
 * Classe abstrata que representa um movimento de estoque.
 * Um movimento sempre possui produto, data, quantidade e valor unitário.
 * As subclasses determinam se o movimento é de entrada ou saída.
 */
public abstract class Movimento {

    protected Produto produto;
    protected LocalDate data;
    protected int quantidade;
    protected double valorUnitario;

    /**
     * Constrói um movimento genérico de estoque.
     *
     * @param produto produto movimentado
     * @param data data do movimento
     * @param quantidade quantidade movimentada
     * @param valorUnitario valor unitário do produto no movimento
     */
    public Movimento(Produto produto, LocalDate data, int quantidade, double valorUnitario) {
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    // -----------------------------------------------------
    // GETTERS
    // -----------------------------------------------------

    /**
     * Retorna o produto associado ao movimento.
     *
     * @return produto movimentado
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Retorna a data do movimento.
     *
     * @return data do movimento
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Retorna a quantidade movimentada.
     *
     * @return quantidade movimentada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna o valor unitário utilizado no movimento.
     *
     * @return valor unitário do produto
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Retorna o valor total do movimento,
     * calculado como quantidade multiplicada pelo valor unitário.
     *
     * @return valor total do movimento
     */
    public double getValorTotal() {
        return quantidade * valorUnitario;
    }

    // -----------------------------------------------------
    // MÉTODO ABSTRATO
    // -----------------------------------------------------

    /**
     * Aplica o movimento ao estoque do produto.
     * Entradas somam quantidade, saídas subtraem.
     */
    public abstract void aplicar();
}
