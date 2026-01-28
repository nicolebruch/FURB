package ServicoTransportes;

public class TransporteTaxi extends ServicoTransporte {

	public TransporteTaxi(String nomeServico) {
		super("Taxi");
		
	}

	@Override
	public double calcularTarifa(double distancia) {
		return distancia * 3.50;
	}

	@Override
	public double calcularTempoEstimado(double distancia) {
		return distancia * 1.5;
	}

	 
	
}
