package ServicoTransportes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

public class TransporteTeste {

	@Test
	@Order(1)
	public void ct01_calcularTarifaOnibus() {
		TransporteOnibus onibus = new TransporteOnibus("Transporte coletivo convencional");
		double resultado = onibus.calcularTarifa(10);
		assertEquals(12.0, resultado, 0.01);
	}

	@Test
	@Order(2)
	public void ct02_calcularTempoMetro() {
		TransporteMetro metro = new TransporteMetro("Transporte coletivo convencional");
		double resultado = metro.calcularTempoEstimado(5);
		assertEquals(5.0, resultado, 0.01);
	}

	@Test
	@Order(3)
	public void ct03_calcularTarifaTaxi() {
		TransporteTaxi taxi = new TransporteTaxi("Transporte coletivo convencional");
		double resultado = taxi.calcularTarifa(8);
		assertEquals(8.0, resultado, 0.01);
	}
}
