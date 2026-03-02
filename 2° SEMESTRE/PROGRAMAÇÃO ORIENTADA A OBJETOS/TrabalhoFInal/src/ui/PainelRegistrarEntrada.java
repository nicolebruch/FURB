package ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import model.Entrada;
import model.Produto;
import service.EstoqueService;

/**
 * Painel responsável pelo registro de entradas no estoque.
 */
public class PainelRegistrarEntrada extends JPanel {

    private EstoqueService service;

    private JComboBox<String> cbProdutos;
    private JTextField txtQuantidade;
    private JTextField txtValor;
    private JTextField txtData;

    /**
     * Constrói o painel de registro de entradas.
     *
     * @param service serviço de estoque utilizado
     */
    public PainelRegistrarEntrada(EstoqueService service) {
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

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
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

        JButton btn = new JButton("Registrar entrada");
        btn.addActionListener(e -> registrar());

        add(form, BorderLayout.NORTH);
        add(btn, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------
    // AÇÃO PRINCIPAL
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

            Entrada entrada = new Entrada(p, data, quantidade, valor);
            service.registrarEntrada(entrada);

            JOptionPane.showMessageDialog(this, "Entrada registrada com sucesso.");
            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar entrada. Verifique os dados.");
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
