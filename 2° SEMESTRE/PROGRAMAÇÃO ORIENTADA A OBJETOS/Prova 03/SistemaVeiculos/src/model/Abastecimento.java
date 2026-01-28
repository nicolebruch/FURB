package model;

public class Abastecimento {

	private double km;
	private double litros;
	private double precoLitro;
	
	public Abastecimento(double km, double litros, double precoLitro) {
		super();
		this.km = km;
		this.litros = litros;
		this.precoLitro = precoLitro;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public double getLitros() {
		return litros;
	}

	public void setLitros(double litros) {
		this.litros = litros;
	}

	public double getPrecoLitro() {
		return precoLitro;
	}

	public void setPrecoLitro(double precoLitro) {
		this.precoLitro = precoLitro;
	}


	
	
}
