package ui;

import javax.swing.*;
import java.awt.*;

import service.EstoqueService;
import service.PersistenciaCSV;
import service.PersistenciaService;

/**
 * Tela principal do sistema de controle de estoque.
 * Gerencia o menu lateral e alterna entre os painéis.
 */
public class TelaPrincipal extends JFrame {

    private CardLayout card;
    private JPanel painelCentral;

    private EstoqueService service;
    private PersistenciaService persistencia;

    private PainelCadastroProduto painelCadastro;
    private PainelRegistrarEntrada painelEntrada;
    private PainelRegistrarSaida painelSaida;
    private PainelConsultarSaldo painelSaldo;
    private PainelListarMovimentos painelMov;
    private PainelListarProdutos painelProd;

    /**
     * Constrói a tela principal.
     */
    public TelaPrincipal() {

        service = new EstoqueService();
        persistencia = new PersistenciaCSV("produtos.csv", "movimentos.csv");

        try {
            PersistenciaService.RegistroPersistido r = persistencia.carregar();
            service.getProdutos().addAll(r.produtos);
            service.getMovimentos().addAll(r.movimentos);
        } catch (Exception ignored) {}

        setTitle("Sistema de Controle de Estoque");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        montarMenu();
        montarPainelCentral();

        setVisible(true);
    }

    // -----------------------------------------------------
    // MENU LATERAL
    // -----------------------------------------------------

    private void montarMenu() {

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setPreferredSize(new Dimension(200, 0));
        menu.setBackground(new Color(230, 230, 230));

        JButton btnCadastro = criarBotao("Produtos");
        JButton btnEntrada = criarBotao("Registrar Entrada");
        JButton btnSaida = criarBotao("Registrar Saída");
        JButton btnSaldo = criarBotao("Consultar Saldo");
        JButton btnMov = criarBotao("Listar Movimentos");
        JButton btnProd = criarBotao("Listar Produtos");
        JButton btnSalvar = criarBotao("Salvar no Arquivo");

        btnCadastro.addActionListener(e -> mostrar("cadastro"));
        btnEntrada.addActionListener(e -> mostrar("entrada"));
        btnSaida.addActionListener(e -> mostrar("saida"));
        btnSaldo.addActionListener(e -> mostrar("saldo"));
        btnMov.addActionListener(e -> mostrar("movimentos"));
        btnProd.addActionListener(e -> mostrar("produtos"));

        btnSalvar.addActionListener(e -> salvar());

        menu.add(btnCadastro);
        menu.add(btnEntrada);
        menu.add(btnSaida);
        menu.add(btnSaldo);
        menu.add(btnMov);
        menu.add(btnProd);
        menu.add(btnSalvar);

        getContentPane().add(menu, BorderLayout.WEST);
    }

    private JButton criarBotao(String nome) {
        JButton b = new JButton(nome);
        b.setFont(new Font("SansSerif", Font.BOLD, 15));
        b.setMaximumSize(new Dimension(180, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFocusPainted(false);
        return b;
    }

    // -----------------------------------------------------
    // PAINÉIS
    // -----------------------------------------------------

    private void montarPainelCentral() {

        painelCentral = new JPanel();
        card = new CardLayout();
        painelCentral.setLayout(card);

        painelCadastro = new PainelCadastroProduto(service);
        painelEntrada = new PainelRegistrarEntrada(service);
        painelSaida = new PainelRegistrarSaida(service);
        painelSaldo = new PainelConsultarSaldo(service);
        painelMov = new PainelListarMovimentos(service);
        painelProd = new PainelListarProdutos(service);

        painelCentral.add(painelCadastro, "cadastro");
        painelCentral.add(painelEntrada, "entrada");
        painelCentral.add(painelSaida, "saida");
        painelCentral.add(painelSaldo, "saldo");
        painelCentral.add(painelMov, "movimentos");
        painelCentral.add(painelProd, "produtos");

        getContentPane().add(painelCentral, BorderLayout.CENTER);
    }

    private void mostrar(String nome) {

        switch (nome) {
            case "entrada":
                painelEntrada.atualizarCombo();
                break;

            case "saida":
                painelSaida.atualizarCombo();
                break;

            case "saldo":
                painelSaldo.atualizarCombo();
                break;

            case "produtos":
                painelProd.atualizar();
                break;

            case "movimentos":
                painelMov.atualizarTabela();
                break;
        }

        card.show(painelCentral, nome);
    }

    // -----------------------------------------------------
    // SALVAR EM ARQUIVO
    // -----------------------------------------------------

    private void salvar() {
        try {
            persistencia.salvar(service.getProdutos(), service.getMovimentos());
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar.");
        }
    }

    /**
     * Executa o sistema.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipal::new);
    }
}
