package model;

import java.time.LocalDate;

/**
 * Representa uma saída de produtos do estoque.
 * Saídas diminuem a quantidade de um produto e possuem
 * um tipo (venda, uso interno, devolução ou outras).
 */
public class Saida extends Movimento {

    private String tipoSaida;

    /**
     * Cria uma nova saída de estoque.
     *
     * @param produto produto movimentado
     * @param data data da saída
     * @param quantidade quantidade que está saindo
     * @param valorUnitario valor unitário do produto
     * @param tipoSaida classificação da saída
     */
    public Saida(Produto produto, LocalDate data, int quantidade,
                 double valorUnitario, String tipoSaida) {
        super(produto, data, quantidade, valorUnitario);
        this.tipoSaida = tipoSaida;
    }

    /**
     * Retorna o tipo de saída (venda, uso interno, devolução ou outras).
     *
     * @return tipo de saída
     */
    public String getTipoSaida() {
        return tipoSaida;
    }

    // -----------------------------------------------------
    // REGRAS DE NEGÓCIO
    // -----------------------------------------------------

    /**
     * Aplica a saída ao estoque, reduzindo a quantidade.
     */
    @Override
    public void aplicar() {
        produto.removerQuantidade(quantidade);
    }
}
