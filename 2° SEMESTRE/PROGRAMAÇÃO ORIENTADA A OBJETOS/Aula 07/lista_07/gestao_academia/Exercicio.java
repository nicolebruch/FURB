package gestao_academia;

public class Exercicio {

	private String nome;
	private int series;
	private int repeticoes;
	private double carga;

	public Exercicio() {
		setNome("");
		setSeries(0);
	    setRepeticoes(0);
	    setCarga(0.0);
	}

	public Exercicio(String nome, int series, int repeticoes, double carga) {
		setNome(nome);
		setSeries(series);
		setRepeticoes(repeticoes);
		setCarga(carga);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}

	public double getCarga() {
		return carga;
	}

	public void setCarga(double carga) {
		this.carga = carga;
	}

	public String getResumo() {
		return "Exercício: " + nome + " | Series: " + series + " | Repetições: " + repeticoes + " | Carga: " + carga;
	}
}
