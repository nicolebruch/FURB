package questao03;

public class Pessoa {

    String nome;
    double peso;
    double altura;

    double calcularImc() {
        return peso / (altura * altura);
    }
}
