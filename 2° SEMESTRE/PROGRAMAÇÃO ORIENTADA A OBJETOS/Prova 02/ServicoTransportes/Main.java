package ServicoTransportes;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    	
    	ArrayList<ServicoTransporte> transportes = new ArrayList<>();
    	transportes.add(new TransporteOnibus("Transporte coletivo convencional"));
    	transportes.add(new TransporteMetro("Transporte subterrâneo rápido"));
    	transportes.add(new TransporteTaxi("Transporte individual sob demanda"));
    	transportes.add(new TransporteBicicleta("Locação de bicicletas compartilhadas"));
    	
    	double distancia = 10;

    	for (ServicoTransporte t : transportes) {
    		System.out.println("Transporte: " + t.getNomeServico());
    		System.out.println("Tarifa: R$: " + t.calcularTarifa(distancia));
    		System.out.println("Transporte: " + t.calcularTempoEstimado(distancia) + " minutos");
    		System.out.println("=================================================");

    	}
    	
    }

}
