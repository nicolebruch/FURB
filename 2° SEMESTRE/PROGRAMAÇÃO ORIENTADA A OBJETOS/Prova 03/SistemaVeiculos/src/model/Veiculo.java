package model;

import java.util.ArrayList;
import java.util.List;

public class Veiculo {

	private String placa;
	private String modelo;
	private String marca;
	private int ano;
	
	private List<Abastecimento> abastecimentos;
	
	

	public Veiculo(String placa, String modelo, String marca, int ano) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.abastecimentos = new ArrayList<>();
	}

	public void adicionarAbastecimento(Abastecimento a) {
		abastecimentos.add(a);
	}
	public List<Abastecimento> getAbastecimentos() {
		return abastecimentos;
	}
	
	public double getUltimaMedia() {
		if (abastecimentos.size() < 2) {
			throw new IllegalStateException("Não tem abastecimento anterior para calcular a média");
		}
		
		Abastecimento atual = abastecimentos.get(abastecimentos.size() - 1);
		Abastecimento anterior = abastecimentos.get(abastecimentos.size() -2);
		
		return (atual.getKm() - anterior.getKm()) /atual.getLitros();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
