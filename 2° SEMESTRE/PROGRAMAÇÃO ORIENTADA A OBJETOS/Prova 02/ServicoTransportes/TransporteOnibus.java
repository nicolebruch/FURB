package ServicoTransportes;

public class TransporteOnibus extends ServicoTransporte{

	public TransporteOnibus(String nomeServico) {
		super("Onibus");
		
	}

	@Override
	public double calcularTarifa(double distancia) {
	return distancia * 1.20;	
	}

	@Override
	public double calcularTempoEstimado(double distancia) {
		return  distancia * 2;
	}
}
