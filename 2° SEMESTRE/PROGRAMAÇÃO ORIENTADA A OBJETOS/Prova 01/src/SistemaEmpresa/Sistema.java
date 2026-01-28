package SistemaEmpresa;

import java.util.List;

public class Sistema {

	private List<Empresa> empresas;
	Empresa empresa;
	Funcionario funcionario;
	Endereco endereco;

	public void gerarRelatorioCompleto() {
		System.out.println("Empresas: " + empresas.size() + empresa.exibirDados() + endereco.exibirDados());
	}


	
}
	
