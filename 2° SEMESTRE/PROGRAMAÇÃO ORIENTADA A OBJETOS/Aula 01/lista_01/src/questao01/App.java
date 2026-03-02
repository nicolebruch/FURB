package questao01;

public class App {
    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa();

        pessoa1.peso = 78.0;
        pessoa1.altura = 1.75;

        System.out.println("O IMC da pessoa é: " + pessoa1.calcularImc());
    }
}
