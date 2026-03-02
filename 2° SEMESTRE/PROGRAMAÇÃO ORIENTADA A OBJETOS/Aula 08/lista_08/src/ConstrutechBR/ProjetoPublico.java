package ConstrutechBR;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProjetoPublico extends Projeto {

	private LocalDate dataInicio;
	private LocalDate dataFim;


	 public ProjetoPublico(String numero, LocalDate dataEntrada, String descricao,
             LocalDate dataInicio, LocalDate dataFim, List<Profissional> profissionais ) {
		 super(numero, dataEntrada, descricao, profissionais);
		 setDataInicio(dataInicio);
	     setDataFim(dataFim);
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		if (dataInicio == null) {
            throw new IllegalArgumentException("Data de início é obrigatória.");
        }
        this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		 if (dataFim == null) {
	            throw new IllegalArgumentException("Data de fim é obrigatória.");
	        }
	        if (dataInicio != null && dataFim.isBefore(dataInicio)) {
	            throw new IllegalArgumentException("Data de fim não pode ser antes da data de início.");
	        }
	        this.dataFim = dataFim;
	    }

	public long calcularDuracao() {
		return ChronoUnit.DAYS.between(dataInicio, dataFim);
	}
	@Override
	public String getTipo() {
		return "publico";
	}

	@Override
	public String getResumo() {
		return "ProjetoPublico [dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", duracaoDias: " + calcularDuracao() +"]";
	}
}
