public class Armas {

    private int dano;
    private int gastoEstamina;

    public Armas(String nome) {
        nome = nome.toLowerCase();

        if (nome.equals("adaga")) {
            dano = 5;
            gastoEstamina = 2;

        } else if (nome.equals("espada")) {
            dano = 10;
            gastoEstamina = 5;

        } else if (nome.equals("machado")) {
            dano = 20;
            gastoEstamina = 10;

        } else {
            throw new IllegalArgumentException("Arma inválida: " + nome);
        }
    }

    public int getDano() {
        return dano;
    }
    public int getGastoEstamina() {
        return gastoEstamina;
    }
}
