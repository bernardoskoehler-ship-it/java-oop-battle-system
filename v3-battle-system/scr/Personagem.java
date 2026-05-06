import java.util.HashMap;
import java.util.Map;
import java.util.Random;

abstract class Personagem {
    Random random = new Random();

    private String nome;

    private int vida;
    private int vidaMaxima = 30;

    private int armadura;

    private int estamina;
    private int estaminaMaxima = 20;

    private HashMap<String, Armas> armas = new HashMap<>();

    public boolean escolherArma(String nome) {
        nome = nome.toLowerCase();

        try {
            armas.put(nome, new Armas(nome));
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Arma inexistente");
            return false;
        }
    }


    private HashMap<String, Integer, Integer> armadura = new HashMap<>();

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

    public boolean estaVivo() {
        return vida > 0;
    }
    public boolean temEstamina() {
        return estamina > 0;
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
            vida -= vida - vidaMaxima;
        }
    }

    private int calcularDano(int dano) {

    }





}
