public class Mago extends Personagem implements HabilidadeEspecial{
    private int mana;

    Mago(String nome, int vida, int mana) {
        super(nome, vida);
        setMana(mana);
    }

    private void setMana(int mana) {
        if(valorPositivo(mana)) {
            this.mana = mana;
            return;
        }
        this.mana = 6;
    }
    public int getMana() {
        return mana;
    }

    @Override
    public int danoBase() {
        return 10;
    }

    @Override
    public void executarAcao(Personagem alvo) {
        double escolha = Math.random();
        if(escolha < 0.6) {
            atacar(alvo);
        }else {
            ataqueEspecial(alvo);
        }
    }

    @Override
    public void ataqueEspecial(Personagem alvo) {
        if(!podeAtacar(alvo)) {
            System.out.println(getNome() +" nao pode usar especial!");
            return;
        }
        if(!temRecurso(mana, 2)) {
            System.out.println(getNome() + " nao tem mana, atacacou normalmente");
            atacar(alvo);
            return;
        }
        int dano = danoBase() * 2;
        dano = aplicarCritico(dano);
        alvo.receberDano(dano);
        mana -= 2;
        System.out.println(getNome() +" atirou uma bola de fogo! Causando " +dano +" de dano em " +alvo.getNome());
    }
}
