public class Arqueiro extends Personagem implements HabilidadeEspecial{
    private int flechas;

    Arqueiro(String nome, int vida, int flechas) {
        super(nome, vida);
        setFlechas(flechas);
    }

    private void setFlechas(int flechas) {
        if(valorPositivo(flechas)) {
            this.flechas = flechas;
            return;
        }
        this.flechas = 6;
    }
    public int getFlechas() {
        return flechas;
    }

    @Override
    public int danoBase() {
        return 7;
    }

    @Override
    public void executarAcao(Personagem alvo) {
        double escolha = Math.random();
        if(escolha < 0.7) {
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
        if(!temRecurso(flechas, 4)) {
            System.out.println(getNome() +" nao tem flechas suficientes, atacou normalmente!");
            atacar(alvo);
            return;
        }
        int dano = danoBase() * 4;
        dano = aplicarCritico(dano);
        alvo.receberDano(dano);
        flechas -= 4;
        System.out.println(getNome() +" disparou uma chuva de flechas! Causando " +dano +" de dano em " +alvo.getNome());
    }

}
