public class Controlador {

    public void iniciarDuelo(Personagem a, Personagem b) {
        if(a == b) {
            System.out.println("Nao e possivel duelar contra si mesmo");
            return;
        }
        int i = 1;
        while(a.estaVivo() && b.estaVivo()) {
            System.out.println(i +" turno");
            i++;
            a.executarAcao(b);

            if(!b.estaVivo()) {
                break;
            }
            b.executarAcao(a);

            System.out.println();
            System.out.println(a.getNome() +": " +a.getVida());
            System.out.println(b.getNome() +": " +b.getVida());
        }
        Personagem vencedor = a.estaVivo() ? a : b;
        Personagem perdedor = a.estaVivo() ? b : a;

        System.out.println(vencedor.getNome() + " ganhou o duelo contra " + perdedor.getNome());
    }
}
