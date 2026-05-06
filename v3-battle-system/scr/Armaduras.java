public class Armaduras {

    private int durabilidade;
    private int gastoEstamina;

    public Armadura(String nome) {
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
}
