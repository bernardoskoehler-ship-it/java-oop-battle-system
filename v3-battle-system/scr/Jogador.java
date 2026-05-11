public class Jogador extends Personagem {

    Jogador(String nome) {
        super(nome);
    }

    public boolean agir(String acao, Personagem alvo){
        acao = acao.toLowerCase();
        switch(acao) {
            case "ataque fraco":
                ataqueFraco(alvo);
                return true;
            case "ataque forte":
                ataqueForte(alvo);
                return true;
            case "descansar":
                descansar();
                return true;
            case "status":
                mostrarStatus();
                return true;
            default:
                return false;
        }
    }
}
