package questao04;

public class Pessoa {

    String nome;
    double peso;
    double altura;

    double calcularImc() {
        return peso / (altura * altura);
    }

    void exibirDetalhesPessoa() {
        System.out.println("Nome: " + nome);
        System.out.println("Peso: " + peso);
        System.out.println("Altura: " + altura);
        System.out.printf("IMC: " + calcularImc());
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"); 
    }
}
