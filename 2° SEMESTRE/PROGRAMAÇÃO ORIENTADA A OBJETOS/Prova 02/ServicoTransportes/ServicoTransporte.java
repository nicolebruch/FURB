package ServicoTransportes;

public abstract class ServicoTransporte implements Transporte {

	private String nomeServico;

	public ServicoTransporte(String nomeServico) {
		setNomeServico(nomeServico);
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
}
	

