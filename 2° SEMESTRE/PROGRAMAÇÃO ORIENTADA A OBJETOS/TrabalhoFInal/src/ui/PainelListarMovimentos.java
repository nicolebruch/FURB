package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import model.Entrada;
import model.Movimento;
import model.Produto;
import model.Saida;
import service.EstoqueService;

/**
 * Painel para listagem dos movimentos de estoque (entradas e saídas),
 * simulando um extrato, com impacto no saldo de cada produto.
 */
public class PainelListarMovimentos extends JPanel {

    private JTable tabela;
    private DefaultTableModel modelo;

    private EstoqueService service;

    /**
     * Cria o painel de listagem de movimentos.
     *
     * @param service serviço de estoque usado como fonte de dados
     */
    public PainelListarMovimentos(EstoqueService service) {
        this.service = service;

        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);

        modelo = new DefaultTableModel(
                new Object[]{"Tipo", "Produto", "Tipo Saída", "Quantidade",
                        "Valor Movimento", "Saldo Qtd Produto",
                        "Saldo Valor Produto", "Data"},
                0
        );

        tabela = new JTable(modelo);
        tabela.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tabela);

        add(new JLabel("Movimentos de Estoque (Extrato)", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        carregarTabela();
    }

    /**
     * Atualiza a tabela com os movimentos atuais.
     */
    public void atualizarTabela() {
        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);

        Map<String, Integer> saldoQtd = new HashMap<>();
        Map<String, Double> saldoValor = new HashMap<>();

        for (Movimento m : service.listarMovimentosOrdenados()) {
            Produto p = m.getProduto();
            String cod = p.getCodigo();

            int qtdAtual = saldoQtd.getOrDefault(cod, 0);
            double valorAtual = saldoValor.getOrDefault(cod, 0.0);

            double valorMov = m.getValorTotal();
            String tipo;
            String tipoSaida = "";

            if (m instanceof Entrada) {
                tipo = "Entrada";
                qtdAtual += m.getQuantidade();
                valorAtual += valorMov;
            } else {
                Saida s = (Saida) m;
                tipo = "Saída";
                tipoSaida = s.getTipoSaida();
                qtdAtual -= m.getQuantidade();
                valorAtual -= valorMov;
            }

            saldoQtd.put(cod, qtdAtual);
            saldoValor.put(cod, valorAtual);

            modelo.addRow(new Object[]{
                    tipo,
                    p.getNome(),
                    tipoSaida,
                    m.getQuantidade(),
                    String.format("R$ %.2f", valorMov),
                    qtdAtual,
                    String.format("R$ %.2f", valorAtual),
                    m.getData().toString()
            });
        }
    }
}
