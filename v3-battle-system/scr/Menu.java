import java.util.Scanner;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    private SistemaCombate sc = new SistemaCombate();

    public void iniciarJogo() {
        System.out.println("Bem vindo a 3 versão do meu Sistema de Batalhas RPJ.");
        System.out.println("=======================================================");
        System.out.println("Primeira vamos criar seu personagem!");

        System.out.println("Digite um nome:");
        String nomeJogador = scan.nextLine();

        Jogador jogador = new Jogador(nomeJogador);

        System.out.println("Agora vamos escolher sua ARMA e sua ARMADURA!");
        System.out.println("Lista de ARMAS:");
        System.out.println("Adaga       dano:   5      gasto de estamina:    2");
        System.out.println("Espada      dano:   10     gasto de estamina:    5");
        System.out.println("Machado     dano:   20     gasto de estamina:    10");
        System.out.println("=======================================================");
        System.out.println("Lista de ARMADURAS:");
        System.out.println("Leve        defesa:  10    redução na estamina:   0");
        System.out.println("Média       defesa:  20    redução na estamina:   5");
        System.out.println("Pesada      defesa:  30    redução na estamina:   10");

        while(true) {
            System.out.println("Escolha sua ARMA:");
            String arma = scan.nextLine();
            if(!jogador.escolherArma(arma)) {
                System.out.println("Arma inserida inválida!");
                continue;
            }
            break;
        }
        while(true) {
            System.out.println("Escolha sua ARMADURA:");
            String armadura = scan.nextLine();
            if(!jogador.escolherArmadura(armadura)) {
                System.out.println("Armadura inserida inválida!");
                continue;
            }
            break;
        }
        Inimigo inimigo = new Inimigo();

        sc.iniciarCombate(jogador, inimigo);


    }
}
