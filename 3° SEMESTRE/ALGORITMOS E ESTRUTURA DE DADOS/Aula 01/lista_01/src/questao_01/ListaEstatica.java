package questao_01;

public class ListaEstatica {

	private int[] info;
	private int tamanho;

	public ListaEstatica() {
		this.info = new int[10];
		this.tamanho = 0;
	}

	private void redimensionar() {
		int novoTamanhoDoVetor = this.info.length + 10;
		int[] novoVetor = new int[novoTamanhoDoVetor];
		
		for (int i = 0; i < this.tamanho; i++) {
			novoVetor[i] = this.info[i];
		}
		
		this.info = novoVetor;
	}

	public void inserir(int valor) {
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

	public int buscar(int valor) {
		for (int i = 0; i < this.tamanho; i++) {
			if (this.info[i] == valor) {
				return i;
			}
		}

		return -1;
	}

	public void retirar(int valor) {
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
		this.info = new int[10];
		this.tamanho = 0;
	}

	public int obterElemento(int posicao) {
		try {
			if (posicao >= this.tamanho) {
				throw new IndexOutOfBoundsException();
			}
			
			return this.info[posicao];
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

}
