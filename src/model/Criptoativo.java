package model;

public class Criptoativo extends AtivoFinanceiro {
    private double cotacaoAtual;

    public Criptoativo(int id, String nome, String sigla, double cotacaoAtual) {
        super(id, nome, sigla);
        this.cotacaoAtual = cotacaoAtual;
    }

    @Override
    public double getCotacaoAtual() {
        return cotacaoAtual;
    }

    public void atualizarCotacao(double novaCotacao) {
        this.cotacaoAtual = novaCotacao;
    }
}