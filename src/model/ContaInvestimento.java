package model;

public class ContaInvestimento extends Conta {
    private String moeda;

    public ContaInvestimento(int id, Empresa empresa, String moeda) {
        super(id, empresa);
        this.moeda = moeda;
    }

    @Override
    public boolean transferir(Conta destino, double valor) {
        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    public String getMoeda() { return moeda; }

    // Sobrecarga (polimorfismo estático)
    public void depositar(int valorInteiro) {
        depositar((double) valorInteiro);
    }
}