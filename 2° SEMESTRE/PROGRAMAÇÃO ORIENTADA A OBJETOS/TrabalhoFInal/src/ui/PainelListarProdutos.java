package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.Produto;
import service.EstoqueService;

/**
 * Painel que exibe todos os produtos cadastrados no sistema.
 */
public class PainelListarProdutos extends JPanel {

    private EstoqueService service;
    private JTable tabela;
    private DefaultTableModel modelo;

    /**
     * Constrói o painel de listagem de produtos.
     *
     * @param service serviço de estoque utilizado
     */
    public PainelListarProdutos(EstoqueService service) {
        this.service = service;

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        montarTabela();
    }

    // -----------------------------------------------------
    // TABELA
    // -----------------------------------------------------

    private void montarTabela() {

        modelo = new DefaultTableModel(
                new Object[]{"Código", "Nome", "Categoria", "Preço", "Estoque"},
                0
        );

        tabela = new JTable(modelo);
        tabela.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        atualizar();
    }

    // -----------------------------------------------------
    // ATUALIZAÇÃO
    // -----------------------------------------------------

    public void atualizar() {
        modelo.setRowCount(0);

        for (Produto p : service.getProdutos()) {
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNome(),
                    p.getCategoria(),
                    String.format("R$ %.2f", p.getPrecoUnitario()),
                    p.getQuantidadeEstoque()
            });
        }
    }
}
