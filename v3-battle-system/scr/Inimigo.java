import java.util.Random;

public class Inimigo extends Personagem{
    Random random = new Random();

    Inimigo(String nome) {
        super(nome);
        escolherEquipamento();
    }

    public String escolherRandom(String... nomes) {

        int numero = random.nextInt(nomes.length);

        return nomes[numero];
    }

    public void escolherEquipamento() {
        escolherArma(escolherRandom("adaga", "espada", "machado"));
        escolherArmadura(escolherRandom("leve", "media", "pesada"));
    }
}
