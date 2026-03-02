package ConstrutechBR;

import java.util.List;

public class RelatorioPorProfissional implements Relatorio {

	 private String idProfissional;

	    public RelatorioPorProfissional(String idProfissional) {
	        setIdProfissional(idProfissional);
	    }

	    public String getIdProfissional() {
			return idProfissional;
		}

		public void setIdProfissional(String idProfissional) {
			if (idProfissional == null || idProfissional.isBlank()) {
				throw new IllegalArgumentException("idProfissional obrigatorio");
			}
			this.idProfissional = idProfissional;
		}

		@Override
	    public String gerar(List<Projeto> projetos) {
	        String texto = "=== RELATÓRIO POR PROFISSIONAL (" + idProfissional + ") ===\n";

	        for (Projeto p : projetos) {
	            boolean encontrado = false;

	            for (Profissional prof : p.getProfissionais()) {
	                if (prof.getId().equals(idProfissional)) {
	                    encontrado = true;
	                    break;
	                }
	            }

	            if (encontrado) {
	                texto = texto + p.getResumo() + "\n";
	            }
	        }

	        return texto;
	    }
}