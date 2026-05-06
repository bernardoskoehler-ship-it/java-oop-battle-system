import java.util.HashMap;
import java.util.Map;
import java.util.Random;

abstract class Personagem {
    Random random = new Random();

    private String nome;

    private int vida;
    private int vidaMaxima = 30;

    private int estamina;
    private int estaminaMaxima = 20;

    HashMap<String, Integer> armas = new HashMap<>();
    HashMap<String, Integer> armadura = new HashMap<>();

    Personagem(String nome) {
        setNome(nome);
        vida = random.nextInt(21) + 10;
    }

    private void setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }else {
            System.out.println("Nome inserido invalido, nome padrao *PG* adotado!");
            this.nome = nome;
        }
    }
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
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




}
