package model;

public abstract class Conta {
    protected int id;
    protected Empresa empresa;
    protected double saldo;

    public Conta(int id, Empresa empresa) {
        this.id = id;
        this.empresa = empresa;
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

    public abstract boolean transferir(Conta destino, double valor);

    public int getId() { return id; }
    public Empresa getEmpresa() { return empresa; }
    public double getSaldo() { return saldo; }
}
