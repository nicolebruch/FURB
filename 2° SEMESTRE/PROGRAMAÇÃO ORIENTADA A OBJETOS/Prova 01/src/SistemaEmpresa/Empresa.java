package SistemaEmpresa;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String nome;
	private String cnpj;
	private List<Funcionario> funcionarios;
	Funcionario funcionario;
	Endereco endereco;

	public Empresa(String nome, String cnpj) {
		setNome(nome);
		this.cnpj = cnpj;
		this.funcionarios = new ArrayList<>();
		setFuncionario(funcionario);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (cnpj == null || cnpj.isBlank()) {
			throw new IllegalArgumentException("cnpj invalido");
		}
		this.cnpj = cnpj;
	}

	public double calcularFolhaSalarial(Funcionario funcionario) {
		double custoTotal = funcionario.getSalario() * funcionarios.size();
		return custoTotal;
	}

	public void aplicarAumento(Funcionario funcionario, double percentual) {
		percentual = percentual * funcionario.getSalario();
	}

	public void adicionarFuncionario(Funcionario funcionario) {
		if(funcionario != null) {
			funcionarios.add(funcionario);
		}
	}

	public void removerFuncionario(Funcionario funcionario) {
		funcionarios.remove(funcionario);
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String exibirDados() {
		return "nome: " + nome + " | cnpj: " + cnpj;
	}
}