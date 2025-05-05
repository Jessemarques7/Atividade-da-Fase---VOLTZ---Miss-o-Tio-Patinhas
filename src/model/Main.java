package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Main {
    public static void main(String[] args) {
        Seguranca seguranca = new Seguranca();
        
        Usuario usuario = new Usuario(1, "João", "joao@email.com", "1234", TipoUsuario.COMUM);
        
        if (!usuario.login(usuario.getEmail(), usuario.getSenha())) {
            System.out.println("Login falhou! Verifique suas credenciais.");
            return;
        }
        
        Empresa empresa = new Empresa(1, "Tech Corp", "1234567890001", usuario);
        
        ContaInvestimento conta = new ContaInvestimento(1, empresa, "BRL");
        conta.depositar(10000);
        
        Criptoativo bitcoin = new Criptoativo(1, "Bitcoin", "BTC", 250000);
        
        Investimento investimentoInicial = new Investimento(1, conta, bitcoin, 0.02, 5000);
        
        List<Investimento> investimentos = new ArrayList<>();
        investimentos.add(investimentoInicial);
        
        Scanner sc = new Scanner(System.in);
        boolean sair = false;

        DecimalFormat df = new DecimalFormat("#,##0.00");

        
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
                    System.out.println("Usuário: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    break;
                case 2:
                    System.out.println("Empresa: " + empresa.getNome());
                    System.out.println("CNPJ: " + empresa.getCnpj());
                    break;
                case 3:
                    System.out.print("Informe o valor para depósito: ");
                    double valorDeposito = sc.nextDouble();
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado. Saldo atual: R$" + conta.getSaldo());
                    break;
                case 4:
                    System.out.print("Informe o valor para saque: ");
                    double valorSaque = sc.nextDouble();
                    if (conta.sacar(valorSaque)) {
                        System.out.println("Saque realizado. Saldo atual: R$" + conta.getSaldo());
                    } else {
                        System.out.println("Saldo insuficiente para saque.");
                    }
                    break;
                case 5:
                    System.out.println("Saldo atual da conta: R$" + conta.getSaldo());
                    break;
                case 6:
                    System.out.println("Realizar novo investimento:");
                    System.out.print("Informe o valor para investir em " + bitcoin.getNome() + ": ");
                    double valorInvestir = sc.nextDouble();
                    
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
                        investimentos.add(novoInvestimento);
                        conta.sacar(valorInvestir);
                        System.out.println("Investimento realizado com sucesso.");
                    }
                    break;                
                case 7:
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
                    Date[] periodo = { new Date(), new Date() }; // Utiliza a data atual para exemplificar
                    Relatorio relatorio = new Relatorio(1, usuario, periodo, investimentos);
                    System.out.println("Resumo do relatório (Lucro/Prejuízo Total): R$" + relatorio.calcularResumo());
                    relatorio.gerarPDF();
                    break;
                case 9:
                    if (seguranca.validarTransacao()) {
                        seguranca.protegerDados();
                    } else {
                        System.out.println("Falha na validação de segurança.");
                    }
                    break;
                case 0:
                    sair = true;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }
            
            System.out.println();
        }
        
        sc.close();
    }
}
