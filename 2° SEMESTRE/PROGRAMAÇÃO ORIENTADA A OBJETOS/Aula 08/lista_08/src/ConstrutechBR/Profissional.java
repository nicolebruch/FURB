package ConstrutechBR;

public class Profissional {

	private String id;
	private String cpf;
	private String nome;

	public Profissional(String id, String cpf, String nome) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null | id.isBlank()) {
			throw new IllegalArgumentException("ID é obrigatório");
		}
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("CPF é obrigatório.");
		}
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome é obrigatório.");
		}
		this.nome = nome;
	}

	public String getResumo() {
		return "Profissional [id=" + id + ", cpf=" + cpf + ", nome=" + nome + "]";
	}

}
