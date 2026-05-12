public class Armaduras {

    private int durabilidade;
    private int gastoEstamina;

    public Armaduras(String nome) {
        nome = nome.toLowerCase();

        if (nome.equals("leve")) {
            durabilidade = 10;
            gastoEstamina = 0;

        } else if (nome.equals("media")) {
            durabilidade = 20;
            gastoEstamina = 5;

        } else if (nome.equals("pesada")) {
            durabilidade = 30;
            gastoEstamina = 10;

        } else {
            throw new IllegalArgumentException("Armadura inválida: " + nome);
        }
    }

    public int getDurabilidade() {
        return durabilidade;
    }
    public int getGastoEstamina() {
        return gastoEstamina;
    }
    public boolean temArmadura() {
        return durabilidade > 0;
    }
    public void diminuirDurabilidade(int valor) {
        durabilidade -= valor;
    }
    public void aumentarDurabilidade(int valor) {
        durabilidade += valor;
    }
}
