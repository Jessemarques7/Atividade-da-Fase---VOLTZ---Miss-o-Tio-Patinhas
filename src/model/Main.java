package model;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Instancia um objeto de segurança para validar transações
        Seguranca seguranca = new Seguranca();

        // Declaração de variáveis principais
        Usuario usuario = null;
        Empresa empresa = null;
        ContaInvestimento conta = null;
        Criptoativo bitcoin = null;
        Investimento investimentoInicial = null;
        List<Investimento> investimentos = new ArrayList<>();

        // Criação do usuário com tratamento de exceção
        try {
            usuario = new Usuario(1, "João", "joao@email.com", "1234", TipoUsuario.COMUM);
        } catch (Exception e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
            return;
        }

        // Autenticação simples (simulação de login)
        if (!usuario.login(usuario.getEmail(), usuario.getSenha())) {
            System.out.println("Login falhou! Verifique suas credenciais.");
            return;
        }

        // Criação da empresa associada ao usuário
        try {
            empresa = new Empresa(1, "Tech Corp", "1234567890001", usuario);
        } catch (Exception e) {
            System.out.println("Erro ao criar empresa: " + e.getMessage());
            return;
        }

        // Criação da conta de investimento e depósito inicial
        try {
            conta = new ContaInvestimento(1, empresa, "BRL");
            conta.depositar(10000);
        } catch (Exception e) {
            System.out.println("Erro ao criar conta de investimento: " + e.getMessage());
            return;
        }

        // Criação do criptoativo (Bitcoin)
        try {
            bitcoin = new Criptoativo(1, "Bitcoin", "BTC", 250000);
        } catch (Exception e) {
            System.out.println("Erro ao criar criptoativo: " + e.getMessage());
            return;
        }

        // Criação de investimento inicial
        try {
            investimentoInicial = new Investimento(1, conta, bitcoin, 0.02, 5000);
            investimentos.add(investimentoInicial); // Lista local de investimentos
        } catch (Exception e) {
            System.out.println("Erro ao criar investimento inicial: " + e.getMessage());
            return;
        }

        // Scanner e variáveis auxiliares
        Scanner sc = new Scanner(System.in);
        boolean sair = false;
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Formatação monetária

        // Menu interativo com operações
        while (!sair) {
            System.out.println("========= Menu =========");
            System.out.println("1 - Ver informações do usuário");
            System.out.println("2 - Ver informações da empresa");
            System.out.println("3 - Depositar na conta");
            System.out.println("4 - Sacar da conta");
            System.out.println("5 - Ver saldo da conta");
            System.out.println("6 - Realizar novo investimento");
            System.out.println("7 - Visualizar investimentos (valor atual e lucro/prejuízo)");
            System.out.println("8 - Gerar relatório");
            System.out.println("9 - Executar validação de segurança");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    // Exibe dados do usuário
                    System.out.println("Usuário: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    break;
                case 2:
                    // Exibe dados da empresa
                    System.out.println("Empresa: " + empresa.getNome());
                    System.out.println("CNPJ: " + empresa.getCnpj());
                    break;
                case 3:
                    // Realiza depósito na conta
                    System.out.print("Informe o valor para depósito: ");
                    double valorDeposito = sc.nextDouble();
                    try {
                        conta.depositar(valorDeposito);
                        System.out.println("Depósito realizado. Saldo atual: R$" + conta.getSaldo());
                    } catch (Exception e) {
                        System.out.println("Erro ao depositar: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Realiza saque da conta
                    System.out.print("Informe o valor para saque: ");
                    double valorSaque = sc.nextDouble();
                    try {
                        if (conta.sacar(valorSaque)) {
                            System.out.println("Saque realizado. Saldo atual: R$" + conta.getSaldo());
                        } else {
                            System.out.println("Saldo insuficiente para saque.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao sacar: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Exibe o saldo da conta
                    System.out.println("Saldo atual da conta: R$" + conta.getSaldo());
                    break;
                case 6:
                    // Cria um novo investimento em criptoativo
                    System.out.println("Realizar novo investimento:");
                    System.out.print("Informe o valor para investir em " + bitcoin.getNome() + ": ");
                    double valorInvestir = sc.nextDouble();

                    try {
                        double quantidadeComprada = valorInvestir / bitcoin.getCotacaoAtual();

                        if (valorInvestir <= 0) {
                            System.out.println("O valor deve ser maior que zero.");
                        } else if (valorInvestir > conta.getSaldo()) {
                            System.out.println("Saldo insuficiente para realizar o investimento.");
                        } else {
                            Investimento novoInvestimento = new Investimento(
                                    investimentos.size() + 1,
                                    conta,
                                    bitcoin,
                                    quantidadeComprada,
                                    valorInvestir
                            );
                            investimentos.add(novoInvestimento); // adiciona à lista
                            conta.sacar(valorInvestir); // desconta do saldo
                            System.out.println("Investimento realizado com sucesso.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao realizar investimento: " + e.getMessage());
                    }
                    break;
                case 7:
                    // Mostra todos os investimentos realizados, com lucro/prejuízo
                    System.out.println("Investimentos realizados:");
                    for (Investimento inv : investimentos) {
                        System.out.println("ID: " + inv.getId() +
                                " | Criptoativo: " + inv.getCriptoativo().getNome() +
                                " | Quantidade: " + inv.getQuantidade() +
                                " | Valor Atual: R$" + df.format(inv.calcularValorAtual()) +
                                " | Lucro/Prejuízo: R$" + df.format(inv.calcularLucroPrejuizo()));
                    }
                    break;
                case 8:
                    // Gera um relatório simples com resumo
                    try {
                        Date[] periodo = {new Date(), new Date()};
                        Relatorio relatorio = new Relatorio(1, usuario, periodo, investimentos);
                        System.out.println("Resumo do relatório (Lucro/Prejuízo Total): R$" + relatorio.calcularResumo());
                        relatorio.gerarPDF(); // simula geração de PDF
                    } catch (Exception e) {
                        System.out.println("Erro ao gerar relatório: " + e.getMessage());
                    }
                    break;
                case 9:
                    // Executa a simulação de validação de segurança
                    if (seguranca.validarTransacao()) {
                        seguranca.protegerDados();
                    } else {
                        System.out.println("Falha na validação de segurança.");
                    }
                    break;
                case 0:
                    // Finaliza o programa
                    sair = true;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }

            System.out.println();
        }

        sc.close(); // Libera o scanner
    }
}
