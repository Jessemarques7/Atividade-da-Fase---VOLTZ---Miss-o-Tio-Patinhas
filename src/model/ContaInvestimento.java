package model;

public class ContaInvestimento {
    private int id;
    private Empresa empresa;
    private double saldo;
    private String moeda;

    public ContaInvestimento(int id, Empresa empresa, String moeda) {
        this.id = id;
        this.empresa = empresa;
        this.moeda = moeda;
        this.saldo = 0;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transferir(ContaInvestimento destino, double valor) {
        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    public int getId() { return id; }
    public Empresa getEmpresa() { return empresa; }
    public double getSaldo() { return saldo; }
    public String getMoeda() { return moeda; }
}
