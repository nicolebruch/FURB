package questao02;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o peso da pessoa:");
        pessoa1.peso = scanner.nextDouble();
        System.out.println("Digite a altura da pessoa:");
        pessoa1.altura = scanner.nextDouble();

        scanner.close();

        System.out.println("O IMC da pessoa é: " + pessoa1.calcularImc());
    }
}
