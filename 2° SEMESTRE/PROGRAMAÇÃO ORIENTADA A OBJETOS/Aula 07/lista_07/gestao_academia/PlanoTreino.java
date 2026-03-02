package gestao_academia;

import java.util.ArrayList;
import java.util.List;

/**
 * Autor do projeto.
 * 
 * @author Nicole Bruch
 */
public class PlanoTreino {

    private String descricao;
    private String nivel;
    private boolean ativo;
    private List<Exercicio> exercicios;
    

	/**
     * Construtor padrão.
     * Inicializa o plano com descrição padrão e inativo.
     */
    public PlanoTreino() {
        setDescricao("Sem descrição");
        setAtivo(false);
        this.exercicios = new ArrayList<>();
    }

    /**
     * Construtor completo.
     * 
     * @param descricao Descrição do plano (não pode ser nula ou vazia)
     * @param nivel Nível do plano (Iniciante, Intermediário, Avançado)
     * @param ativo Status inicial do plano
     * @throws IllegalArgumentException se a descrição for nula ou vazia
     */
    public PlanoTreino(String descricao, String nivel, boolean ativo) {
        setDescricao(descricao);
        setNivel(nivel);
        setAtivo(ativo);
        this.exercicios = new ArrayList<>();
    }
    
    public List<Exercicio> getExercicios() {
		return exercicios;
	}

    /**
     * Retorna a descrição do plano.
     * 
     * @return descrição do plano
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do plano.
     * 
     * @param descricao nova descrição (não pode ser nula ou vazia)
     * @throws IllegalArgumentException se a descrição for inválida
     */
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida");
        }
        this.descricao = descricao;
    }

    /**
     * Retorna o nível do plano.
     * 
     * @return nível do plano (Iniciante, Intermediário ou Avançado)
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Define o nível do plano.
     * 
     * @param nivel Nível do plano (Iniciante, Intermediário ou Avançado)
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Retorna o status do plano.
     * 
     * @return true se o plano estiver ativo, false caso contrário
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * Define o status do plano.
     * 
     * @param ativo true para ativar, false para desativar
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * Ativa o plano de treino.
     * 
     * @return true se o plano estiver ativo após o método
     */
    public boolean ativar() {
        if (ativo) {
            System.out.println("Seu plano de treino já está ativo.");
        } else {
            ativo = true;
            System.out.println("Plano de treino ativado.");
        }
        return ativo;
    }

    /**
     * Desativa o plano de treino.
     * 
     * @return false se o plano estiver desativado após o método
     */
    public boolean desativar() {
        if (!ativo) {
            System.out.println("Seu plano de treino já está desativado.");
        } else {
            ativo = false;
            System.out.println("Plano de treino desativado.");
        }
        return ativo;
    }
}
