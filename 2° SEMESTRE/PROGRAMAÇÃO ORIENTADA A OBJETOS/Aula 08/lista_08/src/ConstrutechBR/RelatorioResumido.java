package ConstrutechBR;

import java.util.List;

public class RelatorioResumido implements Relatorio {

    @Override
    public String gerar(List<Projeto> projetos) {
        String texto = "=== RELATÓRIO RESUMIDO ===\n";
        for (Projeto p : projetos) {
            texto = texto + p.getNumero() + " - " + p.getDescricao() + "\n";
        }
        return texto;
    }
}