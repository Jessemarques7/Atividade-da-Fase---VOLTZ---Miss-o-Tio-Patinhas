package model;

public class Empresa {
    private int id;
    private String nome;
    private String cnpj;
    private Usuario usuario;

    public Empresa(int id, String nome, String cnpj, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.usuario = usuario;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCnpj() { return cnpj; }
    public Usuario getUsuario() { return usuario; }
}
