package ServicoTransportes;

public class TransporteMetro extends ServicoTransporte{

	public TransporteMetro(String nomeServico) {
		super("Metro");
	
	}

	@Override
	public double calcularTarifa(double distancia) {
		return distancia * 2.0;
	}

	@Override
	public double calcularTempoEstimado(double distancia) {
		return distancia * 1;
	}
	}		
	
