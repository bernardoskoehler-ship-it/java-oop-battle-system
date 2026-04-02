abstract class Personagem {
    private String nome;
    private int vida;

    Personagem(String nome, int vida) {
        setNome(nome);
        setVida(vida);
    }

    private void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "SemNome";
        }
    }
    public String getNome() {
        return nome;
    }

    private void setVida(int vida) {
        if (valorPositivo(vida)) {
            this.vida = vida;
        } else {
            this.vida = 1;
        }
    }
    public int getVida() {
        return vida;
    }

    protected boolean valorPositivo(int valor) {
        return valor > 0;
    }
    protected boolean estaVivo() {
        return getVida() > 0;
    }
    protected boolean podeAtacar(Personagem alvo) {
        return estaVivo() && alvo.estaVivo() && this != alvo;
    }
    protected boolean temRecurso(int recurso, int limite) {
        return recurso >= limite;
    }

    protected int modificarDano(int dano) {
        return dano;
    }
    protected int aplicarCritico(int dano) {
        if (Math.random() < 0.2) {
            dano *= 2;
            System.out.println(getNome() +" acertou um CRITICO!");
        }
        return dano;
    }

    public void receberDano(int dano) {
        if(dano <= 0) {
            return;
        }

        dano = modificarDano(dano);

        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    public void atacar(Personagem alvo) {
        if (!podeAtacar(alvo)) {
            System.out.println(getNome() + " nao pode atacar!");
            return;
        }
        int dano = danoBase();
        dano = aplicarCritico(dano);

        alvo.receberDano(dano);
        System.out.println(getNome() + " atacou " + alvo.getNome() + " causando " + dano + " de dano!");
    }

    protected abstract int danoBase();
    public abstract void executarAcao(Personagem alvo);
}
