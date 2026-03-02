package gestao_academia;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Instrutor extends Pessoa {

    private String cref;
    private String especialidade;
    private List<Aluno> alunos;
    private Instrutor mentor;

    public Instrutor() {
        super("Sem nome", "000.000.000-00");
        this.alunos = new ArrayList<>();
        this.mentor = null;
    }
    public Instrutor(String nome, String cpf, String cref, String especialidade, Instrutor mentor) {
        super(nome, cpf); 
        this.cref = cref;
        this.especialidade = especialidade;
        this.alunos = new ArrayList<>();
        this.mentor = mentor;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Instrutor getMentor() {
        return mentor;
    }

    public void setMentor(Instrutor mentor) {
        this.mentor = mentor;
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null) {
            alunos.add(aluno);
        }
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    @Override
    public String getResumo() {
        return "Instrutor: " + getNome() +
               " | CREF: " + cref +
               " | Especialidade: " + especialidade;
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
