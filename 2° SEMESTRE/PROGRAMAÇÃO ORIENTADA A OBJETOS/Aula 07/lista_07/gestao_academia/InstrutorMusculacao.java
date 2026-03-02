package gestao_academia;

public class InstrutorMusculacao extends Instrutor {

    public InstrutorMusculacao(String nome, String cpf, String cref, String especialidade, Instrutor mentor) {
        super(nome, cpf, cref, especialidade, mentor); 
    }

    @Override
    public String getResumo() {
        return "Instrutor: " + getNome() +
               " | CREF: " + getCref() +
               " | Especializado em hipertrofia muscular";
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
