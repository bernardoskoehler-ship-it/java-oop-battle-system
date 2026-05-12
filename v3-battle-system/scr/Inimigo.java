import java.util.Random;

public class Inimigo extends Personagem{
    private static Random random = new Random();

    Inimigo() {
        super(escolherRandom("Gioberto", "Luiz", "Fabio", "Marcelo", "Damiando", "Natalia"));
        escolherEquipamento();
    }

    private static String escolherRandom(String... nomes) {

        int numero = random.nextInt(nomes.length);

        return nomes[numero];
    }

    public void escolherEquipamento() {
        escolherArma(escolherRandom("adaga", "espada", "machado"));
        escolherArmadura(escolherRandom("leve", "media", "pesada"));
    }

    public void agir(Personagem alvo) {
        if(!temEstamina(getCustoEstaminaArma())) {
            descansar();
            return;
        }
        if(getVida() < 5) {
            if(curar()) {
                return;
            }
        }
        int numero = random.nextInt(2);
        if(numero == 0 || !temEstamina(getCustoEstaminaArma() + 5)) {
            ataqueFraco(alvo);
        } else {
            ataqueForte(alvo);
        }
    }
}
