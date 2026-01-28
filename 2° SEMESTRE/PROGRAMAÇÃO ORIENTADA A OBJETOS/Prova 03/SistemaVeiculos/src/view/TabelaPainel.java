package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TabelaPainel extends JPanel {

	public JTable tabela;
	public DefaultTableModel modelo;
	public JButton btnAbastecer;
	public JButton btnMedia;

	public TabelaPainel() {
		setLayout(new BorderLayout());

		modelo = new DefaultTableModel(new String[]{
				"Placa", "Modelo", "Marca", "Ano", "Qtd. Abastecimentos"
		}, 0);

		tabela = new JTable(modelo);
		JScrollPane scroll = new JScrollPane(tabela);
		add(scroll, BorderLayout.CENTER);

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		btnAbastecer = new JButton("Adicionar Abastecimento");
		btnMedia = new JButton("Calcular Última Média");

		painelBotoes.add(btnAbastecer);
		painelBotoes.add(btnMedia);

		add(painelBotoes, BorderLayout.SOUTH);
	}

	public void limparTabela() {
		modelo.setRowCount(0);
	}

	public void adicionarLinha(Object[] dados) {
		modelo.addRow(dados);
	}

	public void definirAcaoBotaoAbastecer(ActionListener acao) {
		btnAbastecer.addActionListener(acao);
	}

	public void definirAcaoBotaoMedia(ActionListener acao) {
		btnMedia.addActionListener(acao);
	}
}