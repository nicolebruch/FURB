package gestao_academia;

/**
 * Autor do projeto.
 * 
 * @author Nicole Bruch
 */
public class Aluno extends Pessoa {

    private String matricula;
    private int idade;
    private double peso;
    private double altura;
    private PlanoTreino planoTreino;
    private Instrutor instrutor;

    public Aluno() {
        super("Sem nome", "000.000.000-00");
        this.matricula = "Sem matricula";
        this.planoTreino = null;
    }

    /**
     * Construtor completo.
     * 
     * @param nome      Nome do aluno
     * @param cpf       CPF do aluno
     * @param matricula Matrícula do aluno
     * @param idade     Idade do aluno (não pode ser negativa)
     * @param peso      Peso do aluno
     * @param altura    Altura do aluno
     */
    public Aluno(String nome, String cpf, String matricula, int idade, double peso, double altura) {
        super(nome, cpf); // chama a pessoa
        this.matricula = matricula;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public PlanoTreino getPlanoTreino() {
        return planoTreino;
    }

    public void setPlanoTreino(PlanoTreino planoTreino) {
        this.planoTreino = planoTreino;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade negativa");
        }
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String getResumo() {
        return "Aluno: " + getNome() + " | Matrícula: " + matricula;
    }

    @Override
    public String toString() {
        return getResumo();
    }
}
