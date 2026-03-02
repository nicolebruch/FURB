package service;

import java.util.List;

import model.Movimento;
import model.Produto;

/**
 * Interface que define os serviços de persistência da aplicação.
 * Permite salvar e recuperar produtos e movimentos.
 */
public interface PersistenciaService {

    /**
     * Salva os produtos e movimentos em armazenamento permanente.
     *
     * @param produtos lista de produtos
     * @param movimentos lista de movimentos de estoque
     * @throws Exception erro na gravação
     */
    void salvar(List<Produto> produtos, List<Movimento> movimentos) throws Exception;

    /**
     * Carrega os produtos e movimentos salvos.
     *
     * @return objeto contendo as listas carregadas
     * @throws Exception erro na leitura
     */
    RegistroPersistido carregar() throws Exception;

    /**
     * Estrutura auxiliar para agrupar produtos e movimentos carregados.
     */
    class RegistroPersistido {
        public List<Produto> produtos;
        public List<Movimento> movimentos;

        /**
         * Cria um registro contendo produtos e movimentos.
         *
         * @param produtos produtos carregados
         * @param movimentos movimentos carregados
         */
        public RegistroPersistido(List<Produto> produtos, List<Movimento> movimentos) {
            this.produtos = produtos;
            this.movimentos = movimentos;
        }
    }
}
