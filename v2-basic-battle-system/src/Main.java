public static void main(String[] args) {
    Personagem[] time = {
            new Guerreiro("Gubs", 50),
            new Mago("Marcio", 35, 10),
            new Arqueiro("Bobim", 40, 20)
    };
    Controlador c = new Controlador();

    c.iniciarDuelo(time[1], time[2]);
}
