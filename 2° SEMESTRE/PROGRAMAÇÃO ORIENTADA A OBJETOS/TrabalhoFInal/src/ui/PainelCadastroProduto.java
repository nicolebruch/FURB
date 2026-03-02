package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.Produto;
import service.EstoqueService;

/**
 * Painel responsável pelo cadastro e listagem de produtos. Permite informar
 * código, nome, categoria, preço e quantidade inicial, além de listar os
 * produtos cadastrados.
 */
public class PainelCadastroProduto extends JPanel {

	private EstoqueService service;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JComboBox<String> cbCategoria;
	private JTextField txtPreco;
	private JTextField txtQuantidade;

	private JTable tabela;
	private DefaultTableModel modelo;

	/**
	 * Constrói o painel de cadastro de produtos.
	 *
	 * @param service serviço de estoque utilizado
	 */
	public PainelCadastroProduto(EstoqueService service) {
		this.service = service;

		setLayout(new BorderLayout(15, 15));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		montarFormulario();
		montarTabela();
	}

	// -----------------------------------------------------
	// FORMULÁRIO
	// -----------------------------------------------------

	private void montarFormulario() {

		JPanel form = new JPanel(new GridBagLayout());
		form.setBackground(Color.WHITE);

		GridBagConstraints gbc;

		// Código
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		form.add(new JLabel("Código:"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtCodigo = new JTextField();
		form.add(txtCodigo, gbc);

		// Nome
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		form.add(new JLabel("Nome:"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtNome = new JTextField();
		form.add(txtNome, gbc);

		// Categoria
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		form.add(new JLabel("Categoria:"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		cbCategoria = new JComboBox<>(new String[] { "Hardware", "Periféricos", "Acessórios", "Outros" });
		form.add(cbCategoria, gbc);

		// Preço
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		form.add(new JLabel("Preço unitário:"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtPreco = new JTextField();
		form.add(txtPreco, gbc);

		// Quantidade
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		form.add(new JLabel("Quantidade inicial:"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtQuantidade = new JTextField();
		form.add(txtQuantidade, gbc);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(e -> salvarProduto());

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(e -> excluirProduto());

		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botoes.add(btnSalvar);
		botoes.add(btnExcluir);

		add(form, BorderLayout.NORTH);
		add(botoes, BorderLayout.SOUTH);
	}

	// -----------------------------------------------------
	// TABELA
	// -----------------------------------------------------

	private void montarTabela() {

		modelo = new DefaultTableModel(new Object[] { "Código", "Nome", "Categoria", "Preço", "Estoque" }, 0);

		tabela = new JTable(modelo);
		tabela.setRowHeight(22);

		JScrollPane scroll = new JScrollPane(tabela);
		add(scroll, BorderLayout.CENTER);

		atualizarTabela();
	}

	// -----------------------------------------------------
	// AÇÕES
	// -----------------------------------------------------

	private void salvarProduto() {

		try {
			String codigo = txtCodigo.getText().trim();
			String nome = txtNome.getText().trim();
			String precoStr = txtPreco.getText().trim();
			String qtdStr = txtQuantidade.getText().trim();

			if (!codigo.matches("\\d+")) {
				JOptionPane.showMessageDialog(this, "O código deve conter apenas números.");
				return;
			}

			if (codigo.isEmpty() || nome.isEmpty() || precoStr.isEmpty() || qtdStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
				return;
			}

			if (service.buscarProdutoPorCodigo(codigo) != null) {
				JOptionPane.showMessageDialog(this, "Código já existente.");
				return;
			}

			double preco = Double.parseDouble(precoStr);
			int quantidade = Integer.parseInt(qtdStr);

			if (preco <= 0) {
				JOptionPane.showMessageDialog(this, "Preço deve ser maior que zero.");
				return;
			}
			if (quantidade < 0) {
				JOptionPane.showMessageDialog(this, "Quantidade não pode ser negativa.");
				return;
			}

			Produto p = new Produto(codigo, nome, (String) cbCategoria.getSelectedItem(), preco, quantidade);

			service.cadastrarProduto(p);
			atualizarTabela();

			JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso.");
			limparCampos();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser numéricos.");
		}
	}

	private void excluirProduto() {
		int linha = tabela.getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um produto.");
			return;
		}

		String codigo = (String) modelo.getValueAt(linha, 0);
		service.getProdutos().removeIf(p -> p.getCodigo().equals(codigo));

		atualizarTabela();
	}

	// -----------------------------------------------------
	// AUXILIARES
	// -----------------------------------------------------

	private void atualizarTabela() {
		modelo.setRowCount(0);

		for (Produto p : service.getProdutos()) {
			modelo.addRow(new Object[] { p.getCodigo(), p.getNome(), p.getCategoria(),
					String.format("R$ %.2f", p.getPrecoUnitario()), p.getQuantidadeEstoque() });
		}
	}

	private void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("");
		cbCategoria.setSelectedIndex(0);
	}
}
