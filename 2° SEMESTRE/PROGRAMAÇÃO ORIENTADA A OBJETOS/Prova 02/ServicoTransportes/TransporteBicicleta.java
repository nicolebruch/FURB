package ServicoTransportes;

public class TransporteBicicleta extends ServicoTransporte{

	public TransporteBicicleta(String nomeServico) {
		super("Bicicleta");
		
	}

	@Override
	public double calcularTarifa(double distancia) {
		return distancia * 0.80;
	}

	@Override
	public double calcularTempoEstimado(double distancia) {
		return distancia * 3;
	}

}
