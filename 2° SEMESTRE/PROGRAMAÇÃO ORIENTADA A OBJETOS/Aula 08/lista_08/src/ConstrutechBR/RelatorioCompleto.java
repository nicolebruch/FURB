package ConstrutechBR;

import java.util.List;

public class RelatorioCompleto implements Relatorio {

    @Override
    public String gerar(List<Projeto> projetos) {
        String texto = "=== RELATÓRIO COMPLETO ===\n";
        for (Projeto p : projetos) {
            texto = texto + p.getResumo() + "\n";
        }
        return texto;
    }
}