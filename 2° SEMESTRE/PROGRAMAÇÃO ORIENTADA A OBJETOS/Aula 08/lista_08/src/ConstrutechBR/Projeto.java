package ConstrutechBR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Projeto {

	private String numero;
	private LocalDate dataEntrada;
	private String descricao;
	private final List<Profissional> profissionais;

	public Projeto(String numero, LocalDate dataEntrada, String descricao, List<Profissional> profissionais) {
		  setNumero(numero);
	      setDataEntrada(dataEntrada);
	      setDescricao(descricao);
	      this.profissionais = new ArrayList<>();
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		if (numero == null || numero.isBlank()) {
			throw new IllegalArgumentException("numero obrigatorio");
		}
		this.numero = numero;
	}
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		if (dataEntrada == null) {
			throw new IllegalArgumentException("dataEntrada obrigatória");
		}
        this.dataEntrada = dataEntrada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		 if (descricao == null || descricao.isBlank()) {
			throw new IllegalArgumentException("descricao obrigatória");
		 }
	        this.descricao = descricao;
	}
	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void adicionarProfissional(Profissional p) {
		if (p != null && !profissionais.contains(p) ) {
			profissionais.add(p);
		}
	}

	//metodo q vai ser herdado as classes filhas (metodo abstrato)
	public abstract String getTipo();

	public String getResumo() {
		return "Projeto [numero=" + numero + ", dataEntrada=" + dataEntrada + ", descricao=" + descricao
				+ ", profissionais=" + profissionais.size() + "]";
	}






}
