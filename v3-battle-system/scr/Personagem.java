import java.util.HashMap;
import java.util.Map;
import java.util.Random;

abstract class Personagem {
    Random random = new Random();

    private String nome;

    private int vida;
    private int vidaMaxima = 30;

    private int estamina;
    private int estaminaMaxima = 30;

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
    private HashMap<String, Armaduras> armadura = new HashMap<>();
    private String nomeArmadura;
    public boolean escolherArmadura(String nome) {
        nome = nome.toLowerCase();
        try {
            armadura.put(nome, new Armaduras(nome));
            nomeArmadura = nome;
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Armadura inexistente");
            return false;
        }
    }


    Personagem(String nome) {
        setNome(nome);
        vida = random.nextInt(21) + 10;
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
            
            if (armaduraAtual.getDurabilidade() < 0) {
                
                removerVida(Math.abs(armaduraAtual.getDurabilidade()));
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
        return true;
    }

    public boolean ataqueFraco(Personagem alvo) {

    Armas armaAtual = arma.get(nomeArma);

    if (armaAtual == null) {
        System.out.println("Nenhuma arma equipada!");
        return false;
    }

    if (!podeAtacar(alvo,
            armaAtual.getGastoEstamina())) {

        return false;
    }

    alvo.calcularDano(armaAtual.getDano());

    removerEstamina(
            armaAtual.getGastoEstamina()
    );

    return true;
}
public boolean ataqueForte(Personagem alvo) {

    Armas armaAtual = arma.get(nomeArma);

    if (armaAtual == null) {
        System.out.println("Nenhuma arma equipada!");
        return false;
    }

    if (!podeAtacar(alvo,
            armaAtual.getGastoEstamina() + 5)) {

        return false;
    }

    alvo.calcularDano(
            armaAtual.getDano() + 5
    );

    removerEstamina(
            armaAtual.getGastoEstamina() + 5
    );

    return true;
}





}
