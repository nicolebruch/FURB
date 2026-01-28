package SistemaEmpresa;

public class Main {

	public static void main(String[] args) {

		// 2 empresas
		Empresa empresa01 = new Empresa("nuvme", "90835");
		Empresa empresa02 = new Empresa("rabbot", "08324");

		// 6 funcionarios
		Funcionario funcionario01 = new Funcionario("Nicole", "09998", 1900.00);
		Funcionario funcionario02 = new Funcionario("Matilde", "8908", 5000.00);
		Funcionario funcionario03 = new Funcionario("Vitor", "0002", 3500.00);
		Funcionario funcionario04 = new Funcionario("Veyda", "90146", 2000.00);
		Funcionario funcionario05 = new Funcionario("Junior", "75356", 100.00);
		Funcionario funcionario06 = new Funcionario("Kaibro", "34563", 9000.00);

		// associando os funcionrios as empresas
		empresa01.adicionarFuncionario(funcionario01);
		empresa01.adicionarFuncionario(funcionario02);
		empresa01.adicionarFuncionario(funcionario03);

		empresa02.adicionarFuncionario(funcionario04);
		empresa02.adicionarFuncionario(funcionario05);
		empresa02.adicionarFuncionario(funcionario06);

		// endereco empresa
		Endereco endereco01 = new Endereco("jair conselo", 32, "Blumenau");

		System.out.println("Relatório Completo");
		System.out.println("Funcionários da empresa " + empresa01.getNome());
		System.out.println(funcionario01.exibirDados());
		System.out.println(funcionario02.exibirDados());
		System.out.println(funcionario03.exibirDados());
		System.out.println("Folha Salarial " + empresa01.getNome());
		System.out.println(empresa01.calcularFolhaSalarial(funcionario01)+ empresa01.calcularFolhaSalarial(funcionario02) + empresa01.calcularFolhaSalarial(funcionario03));
		System.out.println("Aumento realizado ao funcionário " + funcionario01.getNome());
		System.out.println("Antes do ajuste: " + funcionario01.getSalario());
		empresa01.aplicarAumento(funcionario01, 150.00);
		funcionario01.aplicaAumento(150.00);
		System.out.println("Depois do ajuste: " + funcionario01.getSalario());


		System.out.println("=========================================");
		
		System.out.println("Funcionários da empresa " + empresa02.getNome());
		System.out.println(funcionario04.exibirDados());
		System.out.println(funcionario05.exibirDados());
		System.out.println(funcionario06.exibirDados());
		
		System.out.println(empresa01.calcularFolhaSalarial(funcionario04) + empresa01.calcularFolhaSalarial(funcionario05) + empresa01.calcularFolhaSalarial(funcionario06));
		
		System.out.println("Endereço " + endereco01.exibirDados());
	

	}
}
