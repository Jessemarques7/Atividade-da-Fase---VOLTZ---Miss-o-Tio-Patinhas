package model;

public class Criptoativo {
    private int id;
    private String nome;
    private String sigla;
    private double cotacaoAtual;

    public Criptoativo(int id, String nome, String sigla, double cotacaoAtual) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cotacaoAtual = cotacaoAtual;
    }

    public void atualizarCotacao(double novaCotacao) {
        this.cotacaoAtual = novaCotacao;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSigla() { return sigla; }
    public double getCotacaoAtual() { return cotacaoAtual; }
}
