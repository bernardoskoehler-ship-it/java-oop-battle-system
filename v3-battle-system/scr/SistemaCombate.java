import java.util.Scanner;

public class SistemaCombate {
    private Scanner scanner = new Scanner(System.in);

    public void iniciarCombate(Jogador jogador, Inimigo inimigo) {
        while(jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("Ataque Fraco: " +jogador.getDanoArma());
            System.out.println("Ataque Forte: " +(jogador.getDanoArma()+5));
            System.out.println("Descansar");
            System.out.println("Curar");
            System.out.println("Analizar");
            System.out.println("Status");

            String acao = scanner.nextLine();
            if(!jogador.agir(acao, inimigo)) {
                continue;
            }
            System.out.println("\n=== NOVO TURNO ===");
            if(!inimigo.estaVivo()) {
                break;
            }
            inimigo.agir(jogador);
            System.out.println("\n=== NOVO TURNO ===");
        }
        System.out.println("Fim do duelo!");
    }

}
