import java.util.HashMap;
import java.util.Random;

abstract class Personagem {
    Random random = new Random();

    private String nome;

    private int vida;
    private int vidaMaxima = 30;

    private int estamina;
    private int estaminaMaxima = 30;

    private int pocoesDeCura = 1;

    private HashMap<String, Armas> arma = new HashMap<>();
    private String nomeArma;

    public boolean escolherArma(String nome) {
        nome = nome.toLowerCase();
        try {
            arma.put(nome, new Armas(nome));
            nomeArma = nome;
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Arma inexistente");
            return false;
        }
    }
    protected void equiparArma(String nome, Armas armaNova) {
        arma.put(nome, armaNova);
        nomeArma = nome;
    }

    public int getDanoArma() {
        if(nomeArma == null) {
            return 0;
        }
        return arma.get(nomeArma).getDano();
    }
    public int getCustoEstaminaArma() {
        if(nomeArma == null) {
            return 0;
        }
        return arma.get(nomeArma).getGastoEstamina();
    }

    private HashMap<String, Armaduras> armadura = new HashMap<>();
    private String nomeArmadura;

    public boolean escolherArmadura(String nome) {
        nome = nome.toLowerCase();
        try {
            armadura.put(nome, new Armaduras(nome));
            nomeArmadura = nome;
            if(estamina > getEstaminaMaxima()) {
                estamina = getEstaminaMaxima();
            }
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Armadura inexistente");
            return false;
        }
    }
    public int getDurabilidadeArmadura() {
        if(nomeArmadura == null) {
            return 0;
        }
        return armadura.get(nomeArmadura).getDurabilidade();
    }

    Personagem(String nome) {
        setNome(nome);
        vida = getVidaMaxima();
        estamina = getEstaminaMaxima();
    }

    private void setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }else {
            System.out.println("Nome inserido invalido, nome padrao *Jogador* adotado!");
            this.nome = "Jogador";
        }
    }
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getEstamina() {
        return estamina;
    }
    public int getEstaminaMaxima() {
        if(nomeArmadura == null) {
            return estaminaMaxima;
        }

        return estaminaMaxima - armadura.get(nomeArmadura).getGastoEstamina();
    }

    public boolean estaVivo() {
        return vida > 0;
    }
    public boolean temEstamina(int custo) {
        return estamina >= custo;
    }
    public boolean podeAtacar(Personagem alvo, int custo) {
        if(!estaVivo()) {
            System.out.println("Você lutou bravamente, porem infelizmento morreu!");
            return false;
        }
        if(alvo == this) {
            System.out.println("Não pode atacar a si mesmo!");
            return false;
        }
        if(!alvo.estaVivo()) {
            System.out.println("O alvo já esta morto!");
            return false;
        }
        if(!temEstamina(custo)) {
            System.out.println("Você não tem estamina o suficiente!");
            return false;
        }
        return true;
    }

    private void removerEstamina(int valor) {
        estamina -= valor;
        if(estamina < 0) {
            estamina = 0;
        }
    }
    private void adicionarEstamina(int valor) {
        estamina += valor;
        if(estamina > getEstaminaMaxima()) {
            estamina = getEstaminaMaxima();
        }
    }

    private void removerVida(int valor) {
        vida -= valor;
        if(vida < 0) {
            vida = 0;
        }
    }
    private void adicionarVida(int valor) {
        vida += valor;
        if(vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    private void calcularDano(int dano) {
        int numero = random.nextInt(10) + 1;

        if (numero >= 8) {
            System.out.println("CRÍTICO!");
            dano *= 2;
        }

        Armaduras armaduraAtual = armadura.get(nomeArmadura);


        if (armaduraAtual != null && armaduraAtual.temArmadura()) {

            armaduraAtual.diminuirDurabilidade((int) (dano * 0.7));

            if (getDurabilidadeArmadura() <= 0) {

                removerVida(Math.abs(getDurabilidadeArmadura()));
                armaduraAtual.aumentarDurabilidade((Math.abs(getDurabilidadeArmadura())));
            }

            removerVida((int) (dano * 0.3));
            return;
        }
        removerVida(dano);
    }

    public boolean descansar() {
        if(!estaVivo()) {
            System.out.println("Você ja está descansando, pra sempre...");
            return false;
        }
        int numero = random.nextInt(11) + 10;
        adicionarEstamina(numero);
        System.out.println(getNome() +" descansou e recuperou " +numero +" de estamina!");
        return true;
    }

    public boolean ataqueFraco(Personagem alvo) {
        Armas armaAtual = arma.get(nomeArma);

        if (armaAtual == null) {
            System.out.println("Nenhuma arma equipada!");
            return false;
        }

        if (!podeAtacar(alvo, getCustoEstaminaArma())) {
            return false;
        }

        alvo.calcularDano(getDanoArma());
        removerEstamina(getCustoEstaminaArma());
        System.out.println(getNome() +" deu um Ataque Fraco em " +alvo.getNome() +", ficando com " +alvo.getVida() +" de vida");
        return true;
    }

    public boolean ataqueForte(Personagem alvo) {
        Armas armaAtual = arma.get(nomeArma);

        if (armaAtual == null) {
            System.out.println("Nenhuma arma equipada!");
            return false;
        }

        if (!podeAtacar(alvo,getCustoEstaminaArma() + 5)) {
            return false;
        }

        alvo.calcularDano(getDanoArma() + 5);
        removerEstamina(getCustoEstaminaArma() + 5);
        System.out.println(getNome() +" deu um Ataque Forte em " +alvo.getNome() +", ficando com " +alvo.getVida() +" de vida");
        return true;
    }
    
    public void mostrarStatus() {
        System.out.println("Nome: " +getNome());
        System.out.println("Vida: " +getVida() +"/" +getVidaMaxima());
        System.out.println("Estamina: " +getEstamina() +"/" +getEstaminaMaxima());
        System.out.println("Arma: " +nomeArma +" | Dano: " +getDanoArma());
        System.out.println("Armadura: " +nomeArmadura +" | Durabilidade: " +getDurabilidadeArmadura());
    }
    public void analizarInimigo(Personagem inimigo) {
        System.out.println("Nome: " +inimigo.getNome());
        System.out.println("Vida: " +inimigo.getVida() +"/" +inimigo.getVidaMaxima());
        System.out.println("Estamina: " +inimigo.getEstamina() +"/" +inimigo.getEstaminaMaxima());
        System.out.println("Arma: " +nomeArma +" | Dano: " +inimigo.getDanoArma());
        System.out.println("Armadura: " +nomeArmadura +" | Durabilidade: " +inimigo.getDurabilidadeArmadura());
    }

    public boolean curar() {
        if(pocoesDeCura < 1) {
            System.out.println("Não tem mais poçes de cura!");
            return false;
        }
        if(!estaVivo()) {
            System.out.println("Não pode curar pois esta morto!");
            return false;
        }
        if(getVida() == getVidaMaxima()) {
            System.out.println("Você ja esta com a vida cheia!");
            return false;
        }
        int numero = random.nextInt(5) + 5;
        adicionarVida(numero);
        pocoesDeCura --;
        return true;
    }
}
