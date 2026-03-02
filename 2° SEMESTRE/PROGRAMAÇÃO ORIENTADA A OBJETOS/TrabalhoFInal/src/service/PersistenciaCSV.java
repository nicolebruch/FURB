package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Entrada;
import model.Movimento;
import model.Produto;
import model.Saida;

/**
 * Implementação do serviço de persistência utilizando arquivos CSV.
 * Os arquivos gerados possuem cabeçalhos e são compatíveis com
 * Excel, LibreOffice e Google Sheets.
 */
public class PersistenciaCSV implements PersistenciaService {

    private String caminhoProdutos;
    private String caminhoMovimentos;

    /**
     * Cria um persistente baseado em arquivos CSV.
     *
     * @param caminhoProdutos caminho para o arquivo de produtos
     * @param caminhoMovimentos caminho para o arquivo de movimentos
     */
    public PersistenciaCSV(String caminhoProdutos, String caminhoMovimentos) {
        this.caminhoProdutos = caminhoProdutos;
        this.caminhoMovimentos = caminhoMovimentos;
    }

    // -----------------------------------------------------
    // SALVAR TODOS OS DADOS
    // -----------------------------------------------------

    /**
     * Salva os produtos e movimentos em arquivos CSV.
     *
     * @param produtos lista de produtos
     * @param movimentos lista de movimentos
     * @throws Exception erro durante a gravação
     */
    @Override
    public void salvar(List<Produto> produtos, List<Movimento> movimentos) throws Exception {
        salvarProdutos(produtos);
        salvarMovimentos(movimentos);
    }

    // -----------------------------------------------------
    // SALVAR PRODUTOS
    // -----------------------------------------------------

    /**
     * Salva os produtos no arquivo CSV, incluindo o cabeçalho.
     *
     * @param produtos lista de produtos
     * @throws IOException erro na escrita
     */
    private void salvarProdutos(List<Produto> produtos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoProdutos))) {

            // Cabeçalho
            bw.write("codigo;nome;categoria;preco;estoque");
            bw.newLine();

            for (Produto p : produtos) {
                bw.write(
                    p.getCodigo() + ";" +
                    "\"" + p.getNome() + "\";" +
                    "\"" + p.getCategoria() + "\";" +
                    p.getPrecoUnitario() + ";" +
                    p.getQuantidadeEstoque()
                );
                bw.newLine();
            }
        }
    }

    // -----------------------------------------------------
    // SALVAR MOVIMENTOS
    // -----------------------------------------------------

    /**
     * Salva entradas e saídas no arquivo CSV.
     *
     * @param movimentos lista de movimentos
     * @throws IOException erro na escrita
     */
    private void salvarMovimentos(List<Movimento> movimentos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoMovimentos))) {

            // Cabeçalho
            bw.write("tipo;codigo;data;quantidade;valor;tipoSaida");
            bw.newLine();

            for (Movimento m : movimentos) {

                String base =
                        m.getProduto().getCodigo() + ";" +
                        m.getData() + ";" +
                        m.getQuantidade() + ";" +
                        m.getValorUnitario();

                if (m instanceof Entrada) {
                    bw.write("ENTRADA;" + base + ";");
                } else {
                    Saida s = (Saida) m;
                    bw.write("SAIDA;" + base + ";\"" + s.getTipoSaida() + "\"");
                }

                bw.newLine();
            }
        }
    }

    // -----------------------------------------------------
    // CARREGAR TODOS OS DADOS
    // -----------------------------------------------------

    /**
     * Carrega os produtos e movimentos a partir dos arquivos CSV.
     *
     * @return registro contendo listas de produtos e movimentos
     * @throws Exception erro na leitura
     */
    @Override
    public RegistroPersistido carregar() throws Exception {
        List<Produto> produtos = carregarProdutos();
        List<Movimento> movimentos = carregarMovimentos(produtos);
        return new RegistroPersistido(produtos, movimentos);
    }

    // -----------------------------------------------------
    // CARREGAR PRODUTOS
    // -----------------------------------------------------

    /**
     * Lê o arquivo de produtos e recria os objetos.
     *
     * @return lista de produtos
     * @throws IOException erro na leitura
     */
    private List<Produto> carregarProdutos() throws IOException {
        File f = new File(caminhoProdutos);
        List<Produto> lista = new ArrayList<>();

        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            br.readLine(); // cabeçalho

            String linha;
            while ((linha = br.readLine()) != null) {

                String[] p = parseCSV(linha);

                String codigo = p[0];
                String nome = p[1];
                String categoria = p[2];
                double preco = Double.parseDouble(p[3]);
                int qtd = Integer.parseInt(p[4]);

                lista.add(new Produto(codigo, nome, categoria, preco, qtd));
            }
        }

        return lista;
    }

    // -----------------------------------------------------
    // CARREGAR MOVIMENTOS
    // -----------------------------------------------------

    /**
     * Lê o arquivo de movimentos e recria entradas e saídas.
     *
     * @param produtos lista de produtos previamente carregados
     * @return lista de movimentos
     * @throws IOException erro na leitura
     */
    private List<Movimento> carregarMovimentos(List<Produto> produtos) throws IOException {
        File f = new File(caminhoMovimentos);
        List<Movimento> lista = new ArrayList<>();

        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            br.readLine(); // cabeçalho

            String linha;
            while ((linha = br.readLine()) != null) {

                String[] p = parseCSV(linha);

                String tipo = p[0];
                String codigoProduto = p[1];
                LocalDate data = LocalDate.parse(p[2]);
                int qtd = Integer.parseInt(p[3]);
                double valorUnit = Double.parseDouble(p[4]);

                Produto prod = produtos.stream()
                        .filter(pr -> pr.getCodigo().equals(codigoProduto))
                        .findFirst()
                        .orElse(null);

                if (prod == null) continue;

                if (tipo.equals("ENTRADA")) {
                    lista.add(new Entrada(prod, data, qtd, valorUnit));
                } else {
                    String tipoSaida = p.length > 5 ? p[5] : "";
                    lista.add(new Saida(prod, data, qtd, valorUnit, tipoSaida));
                }
            }
        }

        return lista;
    }

    // -----------------------------------------------------
    // PARSER CSV (SUPORTA ASPAS)
    // -----------------------------------------------------

    /**
     * Converte uma linha CSV em um vetor de campos, respeitando aspas.
     *
     * @param linha linha completa do arquivo CSV
     * @return vetor de campos extraídos
     */
    private String[] parseCSV(String linha) {

        List<String> valores = new ArrayList<>();
        boolean dentroAspas = false;
        StringBuilder atual = new StringBuilder();

        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);

            if (c == '"') {
                dentroAspas = !dentroAspas;
                continue;
            }

            if (c == ';' && !dentroAspas) {
                valores.add(atual.toString());
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }

        valores.add(atual.toString());
        return valores.toArray(new String[0]);
    }
}
