package SistemaEmpresa;

public class Funcionario {

	private String nome;
	private String cpf;
	private double salario;
	
	public Funcionario(String nome, String cpf, double salario) {
		this.nome = nome;
		setCpf(cpf);
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("cpf invalido");
		}
		this.cpf = cpf;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		if (salario <= 0) {
			throw new IllegalArgumentException("Salario invalido");
		}
		this.salario = salario;
	}
	
		public void aplicaAumento(double percentual) {
			salario = percentual * salario;
		}
		
		public String exibirDados() {
			return "nome: " + nome + " | cpf: " + cpf + " | salario: " + salario;
		}
	}
