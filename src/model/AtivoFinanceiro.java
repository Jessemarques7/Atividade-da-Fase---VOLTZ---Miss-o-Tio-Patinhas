package model;

public abstract class AtivoFinanceiro {
    protected int id;
    protected String nome;
    protected String sigla;

    public AtivoFinanceiro(int id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSigla() { return sigla; }

    public abstract double getCotacaoAtual();
}

