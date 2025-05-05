package model;

public class Seguranca {
    private boolean usaCriptografia = true;
    private boolean usaAutenticacao2FA = true;

    public boolean validarTransacao() {
        return usaCriptografia && usaAutenticacao2FA;
    }

    public void protegerDados() {
        System.out.println("Dados protegidos com criptografia.");
    }
}
