package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Veiculo;

public class SistemaVeiculos extends JFrame {

	private VeiculoPainel veiculoPainel;
	private TabelaPainel tabelaPainel;
	private model.SistemaVeiculos sistema;

	public SistemaVeiculos() {
		setTitle("Sistema de Veículos");
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		sistema = new model.SistemaVeiculos();

		veiculoPainel = new VeiculoPainel();
		tabelaPainel = new TabelaPainel();

		add(veiculoPainel, BorderLayout.NORTH);
		add(tabelaPainel, BorderLayout.CENTER);

		veiculoPainel.definirAcaoBotaoCadastrar(new AcaoCadastrar());
		tabelaPainel.definirAcaoBotaoAbastecer(new AcaoAbastecer());
		tabelaPainel.definirAcaoBotaoMedia(new AcaoMedia());

		atualizarTabela();
	}

	private void atualizarTabela() {
		tabelaPainel.limparTabela();

		for (Veiculo v : sistema.getListaVeiculos()) {
			tabelaPainel.adicionarLinha(new Object[] { v.getPlaca(), v.getModelo(), v.getMarca(), v.getAno(),
					v.getAbastecimentos().size() });
		}
	}

	private class AcaoCadastrar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String placa = veiculoPainel.txtPlaca.getText();
				String modelo = veiculoPainel.txtModelo.getText();
				String marca = veiculoPainel.txtMarca.getText();
				int ano = Integer.parseInt(veiculoPainel.txtAno.getText());

				sistema.cadastrarVeiculo(placa, modelo, marca, ano);
				atualizarTabela();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			}
		}
	}

	private class AcaoAbastecer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int linha = tabelaPainel.tabela.getSelectedRow();
			if (linha == -1) {
				JOptionPane.showMessageDialog(null, "Selecione um veiculo");
				return;
			}
			try {
				double km = Double.parseDouble(JOptionPane.showInputDialog("KM Atual:"));
				double litros = Double.parseDouble(JOptionPane.showInputDialog("Litros:"));
				double preco = Double.parseDouble(JOptionPane.showInputDialog("Preco por litro:"));

				sistema.adicionarAbastecimento(linha, km, litros, preco);
				atualizarTabela();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro");
			}
		}
	}

	private class AcaoMedia implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int linha = tabelaPainel.tabela.getSelectedRow();
			if (linha == -1) {
				JOptionPane.showMessageDialog(null, "Selecione um veiculo");
				return;
			}
			try {
				double media = sistema.calcularUltimaMedia(linha);
				JOptionPane.showMessageDialog(null, "Media " + media + "km/l");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		public VeiculoPainel getVeiculoPainel() {
			return veiculoPainel;
		}

		public TabelaPainel getTabelaPainel() {
			return tabelaPainel;
		}
	}
}
