public class Guerreiro extends Personagem implements Defesa {

    private boolean defendendo;

    Guerreiro(String nome, int vida) {
        super(nome, vida);
    }

    @Override
    public int danoBase() {
        return 15;
    }

    @Override
    public void executarAcao(Personagem alvo) {
        double escolha = Math.random();
        if(escolha < 0.7) {
            atacar(alvo);
        }else {
            if(isDefendendo()) {
                atacar(alvo);
            }else {
                defender();
            }
        }
    }

    @Override
    public void defender() {
        if(isDefendendo()){
            System.out.println(getNome() +" ja esta defendendo!");
            return;
        }
        defendendo = true;
        System.out.println(getNome() +" esta defendendo!");
    }
    @Override
    public boolean isDefendendo() {
        return defendendo;
    }
    @Override
    protected int modificarDano(int dano) {
        dano = super.modificarDano(dano);
        if(isDefendendo()) {
            dano /= 2;
            defendendo = false;
            System.out.println(getNome() +" teve a defesa quebrada");
        }
        return dano;
    }

}
