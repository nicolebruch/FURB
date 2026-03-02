package ConstrutechBR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Profissional engenheira = new Profissional("P01", "111.111.111-11", "Ana Engenheira");
        Profissional arquiteto = new Profissional("P02", "222.222.222-22", "Bruno Arquiteto");

        ProjetoPublico projetoPublico = new ProjetoPublico(
                "001",
                LocalDate.of(2025, 1, 15),
                "Construção da Ponte do Rio Azul",
                LocalDate.of(2025, 2, 1),
                LocalDate.of(2025, 5, 15), null
        );
        projetoPublico.adicionarProfissional(engenheira);
        projetoPublico.adicionarProfissional(arquiteto);

        EmpreendimentoPrivado projetoPrivado = new EmpreendimentoPrivado(
                "002",
                LocalDate.of(2025, 3, 5),
                "Condomínio Jardim das Flores",
                false,
                12000.0, null
        );
        projetoPrivado.adicionarProfissional(arquiteto);

        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projetoPublico);
        projetos.add(projetoPrivado);

        Relatorio relatorioCompleto = new RelatorioCompleto();
        Relatorio relatorioPorTipoPublico = new RelatorioPorTipo("publico");
        Relatorio relatorioPorTipoPrivado = new RelatorioPorTipo("privado");
        Relatorio relatorioPorProfissional = new RelatorioPorProfissional("P02");
        Relatorio relatorioResumido = new RelatorioResumido();

        System.out.println(relatorioCompleto.gerar(projetos));
        System.out.println(relatorioPorTipoPublico.gerar(projetos));
        System.out.println(relatorioPorTipoPrivado.gerar(projetos));
        System.out.println(relatorioPorProfissional.gerar(projetos));
        System.out.println(relatorioResumido.gerar(projetos));
    }
}
