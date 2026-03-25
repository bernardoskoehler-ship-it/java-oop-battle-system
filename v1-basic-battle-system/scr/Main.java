abstract class Personagem {
    private String nome;
    private int vida;
    private int vidaMaxima = 100;
    private boolean defendendo;

    Personagem(String nome, int vida) {
        setNome(nome);
        setVida(vida);
    }

    protected void setNome(String nome) {
        if(nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
            return;
        }
        System.out.println("Nome inserido invalido!");
        this.nome = "Null";
    }
    String getNome() {
        return nome;
    }

    protected void setVida(int vida) {
        if(!valorValido(vida) || vida > vidaMaxima) {
            System.out.println("Vida inserida invalida!");
            this.vida = 10;
            return;
        }
        this.vida = vida;
    }
    int getVida() {
        return vida;
    }
    boolean getDefendendo() {
        return defendendo;
    }

    protected boolean valorValido(int valor){
        return valor > 0;
    }
    boolean estaVivo() {
        return vida > 0;
    }
    protected boolean podeAgir(Personagem alvo) {
        return estaVivo() && alvo.estaVivo() && this != alvo;
    }

    protected void receberDano(int dano) {
        if(getDefendendo()) {
            vida -= dano / 2;
            defendendo = false;
            System.out.println(getNome() + " teve a defesa quebrada");
        }else {
            vida -= dano;
        }
        if(getVida() < 0) {
            vida = 0;
        }
    }
    abstract void atacar(Personagem alvo);
    abstract void ataqueEspecial(Personagem alvo);

    protected void defender() {
        if(!estaVivo()) {
            System.out.println(getNome() +" nao pode defender pois esta morto");
            return;
        }
        if(!defendendo) {
            System.out.println(getNome() +" agora esta defendendo");
            defendendo = true;
        }else {
            System.out.println(getNome() +" ja esta defendendo");
        }
    }
    protected void curar(int cura) {
        if(!valorValido(cura) || !estaVivo()) {
            System.out.println("Cura invalida");
            return;
        }
        vida += cura;
        System.out.println(getNome() +" Curou " +cura +" de vida!");
        if(getVida() > vidaMaxima) {
            vida = vidaMaxima;
        }
    }
    protected void executarAcao(Personagem alvo, boolean especial) {
        if(!podeAgir(alvo)) {
            System.out.println(getNome() +" nao pode agir");
            return;
        }
        if(especial) {
            System.out.println(getNome() +" esta tentando usar especial em " +alvo.getNome());
            ataqueEspecial(alvo);
            return;
        }
        System.out.println(getNome() +" atacou " +alvo.getNome());
        atacar(alvo);
    }
    protected void mostrarStatus() {
        System.out.println("Nome: " +getNome());
        System.out.println("Vida: " +getVida() +"/" +vidaMaxima);
    }
}
class Guerreiro extends Personagem {
    private int forca = 20;

    Guerreiro(String nome, int vida) {
        super(nome, vida);
    }
    int getForca() {
        return forca;
    }
    @Override
    protected void atacar(Personagem alvo) {
        alvo.receberDano(getForca());
    }
    @Override
    protected void ataqueEspecial(Personagem alvo) {
        alvo.receberDano(getForca() * 2);
    }
    @Override
    public void mostrarStatus() {
        super.mostrarStatus();
        System.out.println("Forca: " +getForca());
    }
}
class Mago extends Personagem {
    private int mana;
    private int danoMagico = 15;

    Mago(String nome, int vida, int mana) {
        super(nome, vida);
        setMana(mana);
    }

    private void setMana(int mana) {
        if(!valorValido(mana)) {
            System.out.println("Valor de mana invalido");
            this.mana = 5;
            return;
        }
        this.mana = mana;
    }
    int getMana() {
        return mana;
    }
    int getDanoMagico() {
        return danoMagico;
    }

    @Override
    protected void atacar(Personagem alvo) {
        if(getMana() <= 0){
            System.out.println(getNome() +" nao tem mana suficiente");
            return;
        }
        alvo.receberDano(getDanoMagico());
        mana --;
    }
    @Override
    protected void ataqueEspecial(Personagem alvo) {
        if(getMana() <= 1){
            System.out.println(getNome() +" nao tem mana suficiente");
            return;
        }
        alvo.receberDano(getDanoMagico() * 2);
        System.out.println(getNome() +" sofreu um recoil de sua magia");
        receberDano(2);
        mana -= 2;
    }
    @Override
    public void mostrarStatus() {
        super.mostrarStatus();
        System.out.println("Mana: " +getMana());
    }
}
class Arqueiro extends Personagem {
    private int flechas;
    private int danoFlecha = 10;

    Arqueiro(String nome, int vida, int flechas) {
        super(nome, vida);
        setFlechas(flechas);
    }

    private void setFlechas(int flechas) {
        if(!valorValido(flechas)) {
            System.out.println("Valor de flechas invalido");
            this.flechas = 5;
            return;
        }
        this.flechas = flechas;
    }
    int getFlechas() {
        return flechas;
    }
    int getDanoFlecha() {
        return danoFlecha;
    }

    @Override
    void atacar(Personagem alvo) {
        if(getFlechas() <= 0){
            System.out.println(getNome() +" nao tem flechas suficientes");
            return;
        }
        alvo.receberDano(getDanoFlecha());
        flechas --;
    }
    @Override
    void ataqueEspecial(Personagem alvo) {
        if(getFlechas() <= 2){
            System.out.println(getNome() +" nao tem flechas suficientes");
            return;
        }
        alvo.receberDano(getDanoFlecha() * 3);
        flechas -= 3;
    }
    @Override
    public void mostrarStatus() {
        super.mostrarStatus();
        System.out.println("Flechas: " +getFlechas());
    }


  
public static void main(String[] args) {
    Personagem[] time = {
            new Guerreiro("Berserk", 50),
            new Mago("Merlin", 50, 10),
            new Arqueiro("Hobbin Wood", 60, 14)
    };
    //*
    for (int i = 0; i < time.length; i++) {
        Personagem atacante = time[i];
        Personagem alvo = time[(i + 1) % time.length];

        atacante.executarAcao(alvo, (int)(Math.random() * 10) >= 7 );
    }
    time[0].mostrarStatus();
    time[1].mostrarStatus();
    time[2].mostrarStatus();
    //*/
}
