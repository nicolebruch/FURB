package ConstrutechBR;

import java.time.LocalDate;
import java.util.List;

public class EmpreendimentoPrivado extends Projeto{

	private boolean areaRural;
	private double metragem;

	public EmpreendimentoPrivado(String numero, LocalDate dataEntrada, String descricao,
            boolean areaRural, double metragem, List<Profissional> profissionais) {
		super(numero, dataEntrada, descricao, profissionais);
		setAreaRural(areaRural);
        setMetragem(metragem);

	}
	public boolean isAreaRural() {
		return areaRural;
	}
	public void setAreaRural(boolean areaRural) {
		this.areaRural = areaRural;
	}
	public double getMetragem() {
		return metragem;
	}
	public void setMetragem(double metragem) {
		if (metragem <= 0) {
            throw new IllegalArgumentException("Metragem deve ser maior que zero.");
        }
        this.metragem = metragem;
	}
	@Override
	public String getTipo() {
		return "privado";
	}

	@Override
	public String getResumo() {
		return "EmpreendimentoPrivado [areaRural=" + areaRural + ", metragem=" + metragem + "]";
	}
}


