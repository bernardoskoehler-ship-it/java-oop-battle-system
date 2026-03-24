abstract static class Personagem {
    String nome;
    int vida = 100;

    Personagem(String nome) {
        this.nome = nome;
    }
    void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }

    boolean estaVivo() {
        return vida > 0;
    }

    abstract void executarAcao(Personagem inimigo);
}
static class Guerreiro extends Personagem {
    private int forca = 15;

    Guerreiro(String nome) {
        super(nome);
    }

    @Override
    void executarAcao(Personagem inimigo) {
        if(!estaVivo()) {
            System.out.println(nome +" nao pode executar acao pois esta morto");
            return;
        }
        if(!inimigo.estaVivo()) {
            System.out.println(inimigo.nome +" ja esta morto");
            return;
        }
        if(vida >= 30) {
            System.out.println(nome +" atacou " +inimigo.nome);
            inimigo.receberDano(forca);
            return;
        }
        System.out.println(nome +" usao habilidade especial em " +inimigo.nome);
        inimigo.receberDano(forca * 2);
    }
}
static class Mago extends Personagem {
    private int mana = 20;

    Mago(String nome) {
        super(nome);
    }

    @Override
    void executarAcao(Personagem inimigo) {
        if(!estaVivo()) {
            System.out.println(nome +" nao pode executar acao pois esta morto");
            return;
        }
        if(!inimigo.estaVivo()) {
            System.out.println(inimigo.nome +" ja esta morto");
            return;
        }
        System.out.println(nome +" atacou " +inimigo.nome +". Mas sofreu um recoil de sua magia!");
        inimigo.receberDano(mana);
        receberDano(5);
    }
}
static class Arqueiro extends Personagem {
    private int precisao = 10;

    Arqueiro(String nome) {
        super(nome);
    }

    @Override
    void executarAcao(Personagem inimigo) {
        if(!estaVivo()) {
            System.out.println(nome +" nao pode executar acao pois esta morto");
            return;
        }
        if(!inimigo.estaVivo()) {
            System.out.println(inimigo.nome +" ja esta morto");
            return;
        }
        if((int)(Math.random() * 10) <= 4 ) {
            System.out.println(nome +" atacou " +inimigo.nome);
            inimigo.receberDano(precisao);
            return;
        }
        System.out.println(nome +" atacou " +inimigo.nome +" e acertou um critico");
        inimigo.receberDano(precisao * 2);
    }
}

public static void main(String[] args) {
    Personagem[] personagens = {
            new Guerreiro("Guts"),
            new Mago("Merlin"),
            new Arqueiro("Legolas")
    };
    for (int i = 0; i < personagens.length; i++) {
        Personagem atacante = personagens[i];
        Personagem alvo = personagens[(i + 1) % personagens.length];

        atacante.executarAcao(alvo);
    }
}
