package model;

import java.util.Date;

public class Investimento {
    private int id;
    private ContaInvestimento conta;
    private Criptoativo criptoativo;
    private double quantidade;
    private Date dataCompra;
    private double valorCompra;

    public Investimento(int id, ContaInvestimento conta, Criptoativo criptoativo, double quantidade, double valorCompra) {
        this.id = id;
        this.conta = conta;
        this.criptoativo = criptoativo;
        this.quantidade = quantidade;
        this.valorCompra = valorCompra;
        this.dataCompra = new Date();
    }

    public double calcularValorAtual() {
        return quantidade * criptoativo.getCotacaoAtual();
    }

    public double calcularLucroPrejuizo() {
        return calcularValorAtual() - valorCompra;
    }

    public int getId() { return id; }
    public ContaInvestimento getConta() { return conta; }
    public Criptoativo getCriptoativo() { return criptoativo; }
    public double getQuantidade() { return quantidade; }
    public Date getDataCompra() { return dataCompra; }
    public double getValorCompra() { return valorCompra; }
}
