package ConstrutechBR;

import java.util.List;

public class RelatorioPorTipo implements Relatorio {

	private String tipo;

    public RelatorioPorTipo(String tipo) {
        setTipo(tipo);

    }



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		if (tipo == null || tipo.isBlank()) {
			throw new IllegalArgumentException("tipo obrigatorio");
		}
		this.tipo = tipo;
	}

	@Override
	public String gerar(List<Projeto> projetos) {
        String texto = "=== RELATÓRIO POR TIPO: " + tipo.toUpperCase() + " ===\n";
        for (Projeto p : projetos) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                texto += p.getResumo() + "\n";
            }
        }
        return texto;
    }
}