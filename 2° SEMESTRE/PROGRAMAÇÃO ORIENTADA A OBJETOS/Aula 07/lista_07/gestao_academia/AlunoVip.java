package gestao_academia;

public class AlunoVip extends Aluno {

    private double descontoMensalidade;

    public AlunoVip(String nome, String cpf, String matricula, int idade, double peso, double altura, double descontoMensalidade) {
        super(nome, cpf, matricula, idade, peso, altura);  // AGORA TEM CPF AQUI!
        this.descontoMensalidade = descontoMensalidade;
    }

    public double getDescontoMensalidade() {
        return descontoMensalidade;
    }

    public void setDescontoMensalidade(double descontoMensalidade) {
        this.descontoMensalidade = descontoMensalidade;
    }

    @Override
    public String getResumo() {
        return "Aluno VIP: " + getNome() +
               " | Matrícula: " + getMatricula() +
               " | Benefícios: Acesso a treinos personalizados e sala exclusiva" +
               " | Desconto Mensalidade: " + descontoMensalidade + "%";
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
