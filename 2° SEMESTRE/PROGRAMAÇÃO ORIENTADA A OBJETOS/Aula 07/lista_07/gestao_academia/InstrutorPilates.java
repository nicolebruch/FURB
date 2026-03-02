package gestao_academia;

public class InstrutorPilates extends Instrutor {

    public InstrutorPilates(String nome, String cpf, String cref, String especialidade, Instrutor mentor) {
        super(nome, cpf, cref, especialidade, mentor);  
    }

    @Override
    public String getResumo() {
        return "Instrutor: " + getNome() +
               " | CREF: " + getCref() +
               " | Especializado em pilates e consciência corporal";
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
