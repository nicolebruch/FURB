package questao03;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pessoa pessoa = new Pessoa();

        for (int i = 0; i < 3; i++) {
            pessoa = new Pessoa();
            System.out.println("Informe o nome da pessoa" + (i + 1) + ":");
            pessoa.nome = scanner.next();
            System.out.println("Informe o peso da pessoa" + (i + 1) + ":");
            pessoa.peso = scanner.nextDouble();
            System.out.println("Informe a altura da pessoa" + (i + 1) + ":");
            pessoa.altura = scanner.nextDouble();

            System.out.println("Pessoa " + pessoa.nome + ":" + pessoa.calcularImc());
        }
        scanner.close();
    }
}