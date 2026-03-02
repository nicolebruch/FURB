package questao04;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pessoa[] pessoa = new Pessoa[3];

        for (int i = 0; i < 3; i++) {
            pessoa[i] = new Pessoa();

            System.out.println("Informe o nome da pessoa " + (i + 1) + ":");
            pessoa[i].nome = scanner.next();

            System.out.println("Informe o peso da pessoa " + (i + 1) + ":");
            pessoa[i].peso = scanner.nextDouble();

            System.out.println("Informe a altura da pessoa " + (i + 1) + ":");
            pessoa[i].altura = scanner.nextDouble();

        }
        scanner.close();

        // ordem inversa de exibição
        for (int i = pessoa.length - 1; i >= 0; i--) {
            pessoa[i].exibirDetalhesPessoa();

        }
    }
}
