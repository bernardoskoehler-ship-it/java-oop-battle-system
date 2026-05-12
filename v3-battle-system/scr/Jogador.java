public class Jogador extends Personagem {

    Jogador(String nome) {
        super(nome);
    }

    public boolean agir(String acao, Personagem alvo){
        acao = acao.toLowerCase();
        switch(acao) {
            case "ataque fraco":
                return ataqueFraco(alvo);
            case "ataque forte":
                return ataqueForte(alvo);
            case "descansar":
                return descansar();
            case "curar":
                return curar();
            case "analizar":
                analizarInimigo(alvo);
                return false;
            case "status":
                mostrarStatus();
                return false;
            default:
                return false;
        }
    }
    
}
