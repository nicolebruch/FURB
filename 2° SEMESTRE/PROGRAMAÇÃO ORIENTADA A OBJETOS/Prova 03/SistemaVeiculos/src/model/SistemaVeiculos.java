package model;

import java.util.List;

public class SistemaVeiculos {

	private RepositorioVeiculos repositorio = new RepositorioVeiculos();
	private final String arquivo = "dados.dat";

	public SistemaVeiculos() {
		carregar();
	}
	
	public void cadastrarVeiculo(String placa, String modelo, String marca, int ano) {
		Veiculo v = new Veiculo(placa, modelo, marca, ano);
		repositorio.adicionar(v);
		salvar();
	}
	
	public List<Veiculo> getListaVeiculos() {
		return repositorio.listar();
	}
	
	public void adicionarAbastecimento(int index, double km, double litros, double preco) {
		Veiculo v = repositorio.listar().get(index);
		v.adicionarAbastecimento(new Abastecimento(km,litros,preco));
		salvar();
	}
	
	public double calcularUltimaMedia(int index) {
		Veiculo v = repositorio.listar().get(index);
		return v.getUltimaMedia();
	}
	
	public void salvar() {
		repositorio.salvar(arquivo);
		
	}
	
	public void carregar() {
		repositorio.carregar(arquivo);
	}
}

