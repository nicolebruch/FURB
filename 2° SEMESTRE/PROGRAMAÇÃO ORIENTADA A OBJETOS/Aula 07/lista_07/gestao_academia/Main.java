package gestao_academia;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        // Instrutores com CPF
        Instrutor mentor1 = new Instrutor("Carlos", "111.111.111-11", "001", "Musculação Avançada", null);
        Instrutor mentor2 = new Instrutor("Ana", "222.222.222-22", "002", "Crossfit Avançado", null);
        
        Instrutor instrutor1 = new Instrutor("Joel", "333.333.333-33", "234", "Musculação", mentor1);
        Instrutor instrutor2 = new Instrutor("Beatriz", "444.444.444-44", "356", "Crossfit", null);    
        
        instrutor1.setMentor(mentor1);

        // Alunos com CPF
        Aluno aluno1 = new Aluno("Giovanna", "555.555.555-55", "12", 18, 48.8, 1.70);
        Aluno aluno2 = new Aluno("Douglas", "666.666.666-66", "31", 36, 76.3, 1.75);
        Aluno alunoBasico = new Aluno("Sem Nome", "777.777.777-77", "00", 0, 0.0, 0.0);
        
        // Associação de alunos aos instrutores
        instrutor1.adicionarAluno(aluno1);
        instrutor2.adicionarAluno(aluno2);
        instrutor1.adicionarAluno(alunoBasico);
        
        Exercicio exercicios1 = new Exercicio("Supino", 3, 12, 40.0);
        Exercicio exercicios2 = new Exercicio("Agachamento", 4, 10, 60.0);
        Exercicio exercicios3 = new Exercicio("Perna", 3, 15, 35.0);

        PlanoTreino treino1 = new PlanoTreino("Treino para resistência", "Avançado", true);
        PlanoTreino treino2 = new PlanoTreino("Treino para força", "Intermediário", false);
        PlanoTreino treinoBasico = new PlanoTreino();

        treino1.getExercicios().add(exercicios1);
        treino1.getExercicios().add(exercicios3);
        
        treino2.getExercicios().add(exercicios2);
        treino2.getExercicios().add(exercicios1);
        
        treinoBasico.getExercicios().add(exercicios3);
        
        // Associação de planos aos alunos
        aluno1.setPlanoTreino(treino1);
        aluno2.setPlanoTreino(treino2);
        alunoBasico.setPlanoTreino(treinoBasico);

        System.out.println("\n===== Instrutores =====");
        System.out.println(instrutor1.getResumo());
        System.out.println(instrutor2.getResumo());

        Aluno[] alunos = {aluno1, aluno2, alunoBasico};
        for (Aluno aluno : alunos) {
            System.out.println("\n===== Plano de Treino do Aluno: " + aluno.getNome() + " =====");
            PlanoTreino plano = aluno.getPlanoTreino();
            System.out.println(plano.getDescricao() + " | Nivel: " + plano.getNivel() + " | Ativo: " + plano.isAtivo());
            
            System.out.println("Exercícios:");
            for (Exercicio exercicio : plano.getExercicios()) {
                System.out.println(" - " + exercicio.getResumo());
            }
        }
        
        System.out.println("\n===== Alterando status dos Planos =====");
        treino1.desativar();
        treino2.ativar();
        
        // ===== Teste de Instrutor Especializado =====
        InstrutorMusculacao instMuscu = new InstrutorMusculacao("Marcos", "888.888.888-88", "777", "Musculação", null);
        InstrutorPilates instPilates = new InstrutorPilates("Fernanda", "999.999.999-99", "888", "Pilates", null);

        System.out.println("\n===== Teste Instrutores Especializados =====");
        System.out.println(instMuscu);
        System.out.println(instPilates);

        // ===== Teste de Aluno VIP =====
        AlunoVip vip = new AlunoVip("Roberta", "000.000.000-00", "99", 25, 60.5, 1.65, 20.0);
        System.out.println("\n===== Teste Aluno VIP =====");
        System.out.println(vip);

        // ===== Demonstração de POLIMORFISMO =====
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(aluno1);
        pessoas.add(instrutor1);
        pessoas.add(instMuscu);
        pessoas.add(instPilates);
        pessoas.add(vip);

        System.out.println("\n===== LISTA DE PESSOAS (POLIMORFISMO) =====");
        for (Pessoa p : pessoas) {
            System.out.println(p.getResumo());
        }
    }
}
