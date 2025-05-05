package model;

import java.util.Date;
import java.util.List;

public class Relatorio {
    private int id;
    private Usuario usuario;
    private Date[] periodo;
    private List<Investimento> investimentos;

    public Relatorio(int id, Usuario usuario, Date[] periodo, List<Investimento> investimentos) {
        this.id = id;
        this.usuario = usuario;
        this.periodo = periodo;
        this.investimentos = investimentos;
    }

    public double calcularResumo() {
        return investimentos.stream()
                .mapToDouble(Investimento::calcularLucroPrejuizo)
                .sum();
    }

    public void gerarPDF() {
        System.out.println("Relatório gerado para o usuário: " + usuario.getNome());
    }
}
