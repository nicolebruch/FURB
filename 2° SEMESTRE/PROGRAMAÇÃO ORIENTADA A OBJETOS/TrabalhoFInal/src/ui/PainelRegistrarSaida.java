package ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import model.Produto;
import model.Saida;
import service.EstoqueService;

/**
 * Painel responsável pelo registro de saídas do estoque.
 */
public class PainelRegistrarSaida extends JPanel {

    private EstoqueService service;

    private JComboBox<String> cbProdutos;
    private JTextField txtQuantidade;
    private JTextField txtValor;
    private JTextField txtData;
    private JComboBox<String> cbTipoSaida;

    /**
     * Constrói o painel de registro de saídas.
     *
     * @param service serviço de estoque utilizado
     */
    public PainelRegistrarSaida(EstoqueService service) {
        this.service = service;

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        montarFormulario();
    }

    // -----------------------------------------------------
    // FORMULÁRIO
    // -----------------------------------------------------

    private void montarFormulario() {

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBackground(Color.WHITE);

        form.add(new JLabel("Produto:"));
        cbProdutos = new JComboBox<>();
        carregarProdutos();
        form.add(cbProdutos);

        form.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        form.add(txtQuantidade);

        form.add(new JLabel("Valor unitário:"));
        txtValor = new JTextField();
        form.add(txtValor);

        form.add(new JLabel("Data (AAAA-MM-DD):"));
        txtData = new JTextField(LocalDate.now().toString());
        form.add(txtData);

        form.add(new JLabel("Tipo da saída:"));
        cbTipoSaida = new JComboBox<>(new String[]{
                "Venda ao cliente",
                "Uso interno",
                "Devolução a fornecedor",
                "Outras saídas"
        });
        form.add(cbTipoSaida);

        JButton btn = new JButton("Registrar saída");
        btn.addActionListener(e -> registrar());

        add(form, BorderLayout.NORTH);
        add(btn, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------
    // REGISTRO DA SAÍDA
    // -----------------------------------------------------

    private void registrar() {
        try {
            String codigo = (String) cbProdutos.getSelectedItem();

            if (codigo == null) {
                JOptionPane.showMessageDialog(this, "Nenhum produto cadastrado.");
                return;
            }

            // quantidade
            if (!txtQuantidade.getText().trim().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser um número inteiro positivo.");
                return;
            }
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.");
                return;
            }

            // valor
            if (!txtValor.getText().trim().matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "Valor unitário inválido.");
                return;
            }
            double valor = Double.parseDouble(txtValor.getText().trim());
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "Valor unitário deve ser maior que zero.");
                return;
            }

            // data
            LocalDate data = LocalDate.parse(txtData.getText().trim());

            Produto p = service.buscarProdutoPorCodigo(codigo);

            String tipo = (String) cbTipoSaida.getSelectedItem();

            Saida saida = new Saida(p, data, quantidade, valor, tipo);

            boolean ok = service.registrarSaida(saida);

            if (!ok) {
                JOptionPane.showMessageDialog(this,
                        "Saída não permitida.\nO saldo ficaria negativo na data informada.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Saída registrada com sucesso.");
            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar saída. Verifique os dados.");
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

    private void limparCampos() {
        txtQuantidade.setText("");
        txtValor.setText("");
        txtData.setText(LocalDate.now().toString());
    }
}
