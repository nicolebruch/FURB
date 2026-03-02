package model;

/**
 * Representa um produto da loja de informática, contendo
 * código, nome, categoria, preço unitário e quantidade em estoque.
 */
public class Produto {

    private String codigo;
    private String nome;
    private String categoria;
    private double precoUnitario;
    private int quantidadeEstoque;

    /**
     * Cria um novo produto.
     *
     * @param codigo código único do produto
     * @param nome nome do produto
     * @param categoria categoria do produto
     * @param precoUnitario preço unitário do produto
     * @param quantidadeEstoque quantidade inicial em estoque
     */
    public Produto(String codigo, String nome, String categoria,
                   double precoUnitario, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // -----------------------------------------------------
    // GETTERS E SETTERS
    // -----------------------------------------------------

    /**
     * Retorna o código do produto.
     *
     * @return código do produto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define o código do produto.
     *
     * @param codigo novo código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria nova categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna o preço unitário do produto.
     *
     * @return preço unitário
     */
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Define o preço unitário do produto.
     *
     * @param precoUnitario novo preço unitário
     */
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Retorna a quantidade em estoque.
     *
     * @return quantidade em estoque
     */
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /**
     * Define a quantidade em estoque.
     *
     * @param quantidadeEstoque nova quantidade em estoque
     */
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // -----------------------------------------------------
    // REGRAS DE NEGÓCIO
    // -----------------------------------------------------

    /**
     * Aumenta a quantidade em estoque do produto.
     *
     * @param quantidade quantidade a ser adicionada
     */
    public void adicionarQuantidade(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    /**
     * Diminui a quantidade em estoque do produto.
     *
     * @param quantidade quantidade a ser removida
     */
    public void removerQuantidade(int quantidade) {
        this.quantidadeEstoque -= quantidade;
    }

    /**
     * Calcula o valor total deste produto em estoque,
     * multiplicando a quantidade pelo preço unitário.
     *
     * @return valor total em estoque
     */
    public double getValorTotal() {
        return quantidadeEstoque * precoUnitario;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}
