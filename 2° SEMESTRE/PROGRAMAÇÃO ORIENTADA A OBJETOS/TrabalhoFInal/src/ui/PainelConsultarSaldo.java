package ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import model.Produto;
import service.EstoqueService;

/**
 * Painel responsável pela consulta de saldo de produtos e
 * consulta do valor total do estoque em um período.
 */
public class PainelConsultarSaldo extends JPanel {

    private EstoqueService service;

    private JComboBox<String> cbProdutos;
    private JLabel lblQuantidade;
    private JLabel lblValorTotal;

    private JTextField txtInicio;
    private JTextField txtFim;
    private JLabel lblSaldoPeriodo;

    /**
     * Constrói o painel de consulta de saldo.
     *
     * @param service serviço de estoque utilizado
     */
    public PainelConsultarSaldo(EstoqueService service) {
        this.service = service;

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        montarConsultaProduto();
        montarConsultaPeriodo();
    }

    // -----------------------------------------------------
    // CONSULTA DE UM PRODUTO ESPECÍFICO
    // -----------------------------------------------------

    private void montarConsultaProduto() {

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBackground(Color.WHITE);

        painel.add(new JLabel("Produto:"));

        cbProdutos = new JComboBox<>();
        carregarProdutos();
        painel.add(cbProdutos);

        painel.add(new JLabel("Quantidade em estoque:"));

        lblQuantidade = new JLabel("-");
        painel.add(lblQuantidade);

        painel.add(new JLabel("Valor total do produto:"));

        lblValorTotal = new JLabel("-");
        painel.add(lblValorTotal);

        JButton btn = new JButton("Consultar produto");
        btn.addActionListener(e -> consultarProduto());

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.add(painel, BorderLayout.CENTER);
        p.add(btn, BorderLayout.SOUTH);

        add(p, BorderLayout.NORTH);
    }

    // -----------------------------------------------------
    // CONSULTA DO ESTOQUE NO PERÍODO
    // -----------------------------------------------------

    private void montarConsultaPeriodo() {

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createTitledBorder("Saldo total no período"));

        painel.add(new JLabel("Data início (AAAA-MM-DD):"));
        txtInicio = new JTextField(LocalDate.now().minusDays(7).toString());
        painel.add(txtInicio);

        painel.add(new JLabel("Data fim (AAAA-MM-DD):"));
        txtFim = new JTextField(LocalDate.now().toString());
        painel.add(txtFim);

        painel.add(new JLabel("Saldo no período:"));
        lblSaldoPeriodo = new JLabel("-");
        painel.add(lblSaldoPeriodo);

        JButton btn = new JButton("Consultar período");
        btn.addActionListener(e -> consultarPeriodo());

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.add(painel, BorderLayout.CENTER);
        p.add(btn, BorderLayout.SOUTH);

        add(p, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------
    // AÇÕES
    // -----------------------------------------------------

    private void consultarProduto() {
        try {
            String codigo = (String) cbProdutos.getSelectedItem();
            if (codigo == null) {
                JOptionPane.showMessageDialog(this, "Nenhum produto cadastrado.");
                return;
            }

            Produto p = service.buscarProdutoPorCodigo(codigo);

            int qtd = p.getQuantidadeEstoque();
            double val = p.getValorTotal();

            lblQuantidade.setText(String.valueOf(qtd));
            lblValorTotal.setText("R$ " + String.format("%.2f", val));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar o produto.");
        }
    }

    private void consultarPeriodo() {
        try {
            LocalDate inicio = LocalDate.parse(txtInicio.getText().trim());
            LocalDate fim = LocalDate.parse(txtFim.getText().trim());

            double saldo = service.calcularSaldoTotalPeriodo(inicio, fim);
            lblSaldoPeriodo.setText("R$ " + String.format("%.2f", saldo));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datas inválidas.");
        }
    }

    // -----------------------------------------------------
    // AUXILIARES
    // -----------------------------------------------------

    public void atualizarCombo() {
        carregarProdutos();
    }

    private void carregarProdutos() {
        cbProdutos.removeAllItems();
        for (Produto p : service.getProdutos()) {
            cbProdutos.addItem(p.getCodigo());
        }
    }
}
