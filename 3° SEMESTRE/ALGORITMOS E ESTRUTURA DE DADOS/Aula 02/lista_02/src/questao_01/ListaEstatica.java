package questao_01;

public class ListaEstatica<T> {

	private Object[] info;
	private int tamanho;

	public ListaEstatica() {
		this.info = new Object[10];
		this.tamanho = 0;
	}

	private void redimensionar() {
		int novoTamanhoDoVetor = this.info.length + 10;
		Object[] novoVetor = new Object[novoTamanhoDoVetor];

		for (int i = 0; i < this.tamanho; i++) {
			novoVetor[i] = this.info[i];
		}

		this.info = novoVetor;
	}

	public void inserir(T valor) {
		if (this.tamanho == this.info.length) {
			this.redimensionar();
		}

		int proximaPosicaoEmBranco = this.tamanho;
		this.info[proximaPosicaoEmBranco] = valor;
		this.tamanho++;
	}

	public void exibir() {
		for (int i = 0; i < this.tamanho; i++) {
			System.out.println(this.info[i]);
		}
	}

	public int buscar(T valor) {
		for (int i = 0; i < this.tamanho; i++) {
			if (this.info[i] == valor) {
				return i;
			}
		}

		return -1;
	}

	public void retirar(T valor) {
		int posicao = this.buscar(valor);

		if (posicao != -1) {
			if (posicao < tamanho - 1) {
				for (int i = posicao; i < this.tamanho - 1; i++) {
					this.info[i] = this.info[i + 1];
				}
			}

			this.tamanho--;
		}
	}

	public void liberar() {
		this.info = new Object[10];
		this.tamanho = 0;
	}

	public T obterElemento(int posicao) {
		try {
			if (posicao >= this.tamanho) {
				throw new IndexOutOfBoundsException();
			}

			return (T) this.info[posicao]; // uso cast aqui pra nao precisar ter que alterar todo o resto depois
		} catch (IndexOutOfBoundsException ex) {
			throw new IndexOutOfBoundsException("Elemento na posição " + posicao + " não foi encontrada");
		}
	}

	public boolean estaVazia() {
		return tamanho == 0;
	}

	public int getTamanho() {
		return tamanho;
	}

	public String toString() {
		String str = "";

		for (int i = 0; i < this.tamanho; i++) {
			str += this.info[i];
			if (i != this.tamanho - 1) {
				str += ",";
			}
		}

		return str;
	}

	public void inverter() {
		// percorre a lista só até metade, porque trocando os elementos das pontas eu
		// nao preciso trocar o do meio
		for (int i = 0; i < this.tamanho / 2; i++) {
			// uso cast pq a lista é object e guardo o elemento da posicao oposta
			T temp = (T) this.info[i];
			// exemplo: posicao 0 recebe elemento que ta na posicao oposta
			this.info[i] = this.info[this.tamanho - 1 - i];
			// posicao oposta recebe o valor guardado no indice 0s
			this.info[this.tamanho - 1 - i] = temp;

		}

	}

}
